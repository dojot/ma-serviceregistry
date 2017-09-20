package br.com.dojot.mutualauthentication.serviceregistry.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.dojot.mutualauthentication.communication.exceptions.InvalidResponseException;
import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.serviceregistry.beans.to.RegistryTO;
import br.com.dojot.mutualauthentication.serviceregistry.beans.to.ServiceTO;
import br.com.dojot.mutualauthentication.serviceregistry.beans.vo.RegistryVO;
import br.com.dojot.mutualauthentication.serviceregistry.dao.api.RegistryDAO;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.LoggingService;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.RegistryService;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.LoggingService.Level;

@Stateless
public class RegistryServiceBean implements RegistryService {

	@EJB
	private RegistryDAO registryDAO;

	@EJB
	private LoggingService loggingService;

	@Override
	public boolean register(RegistryTO to) {
		boolean status = false;
		RegistryVO vo = new RegistryVO();
		vo.setMicroservice(to.getMicroservice());
		vo.setVersion(to.getVersion());
		vo.setContext(to.getContext());
		vo.setHostname(to.getHostname());
		vo.setPort(new Integer(to.getPort()));
		vo.setDate(new Date());
		registryDAO.add(vo);
		status = true;
		return status;
	}

	@Override
	public boolean unregister(RegistryTO to) {
		boolean status = false;
		registryDAO.remove(to.getMicroservice(), to.getVersion(), to.getHostname());
		status = true;
		return status;
	}

	@Override
	public ServiceTO findService(String microservice, String version, String restful, String method) {
		ServiceTO to = null;
		List<RegistryVO> tmp = registryDAO.search(microservice, version);
		if (tmp != null && tmp.size() > 0) {
			int index = new Random().nextInt(tmp.size());
			RegistryVO vo = tmp.get(index);
			to = new ServiceTO();
			to.setMethod(method);
			to.setRestful(restful);
			to.setMicroservice(microservice);
			to.setVersion(version);
			to.setPath("/rest/" + restful + "/" + method);
			to.setTarget("http://" + vo.getHostname() + ":" + vo.getPort() + vo.getContext());
		}
		System.out.println("ServiceRegistry.URL: " + to.getTarget() + to.getPath());
		return to;
	}

	@Override
	public void checkServices() {
		List<RegistryVO> microservices = registryDAO.search();

		loggingService.saveLogging(Level.INFO, "SERVICEREGISTRY", "SISTEMA", "Iniciando verificacao dos microservicos.");
		for (RegistryVO microservice : microservices) {
			boolean isServiceUp = false;
			try {
				isServiceUp = isServiceOnline(microservice.getHostname(), microservice.getPort(),
						microservice.getContext());
				loggingService.saveLogging(Level.INFO, "SERVICEREGISTRY", "SISTEMA",
						microservice.getMicroservice() + " esta online no host " + microservice.getHostname());
			} catch (InvalidResponseException e) {
				loggingService.saveLogging(Level.ERROR, "SERVICEREGISTRY", "SISTEMA", "Erro na chamada REST para checar o status do microservico. " + e.getMessage());
			}
			if (!isServiceUp) {
				loggingService.saveLogging(Level.ERROR, "SERVICEREGISTRY", "SISTEMA",
						microservice.getMicroservice() + " esta offline no host " + microservice.getHostname());
				registryDAO.remove(microservice.getMicroservice(), microservice.getVersion(),
						microservice.getHostname());
			}
		}
		loggingService.saveLogging(Level.INFO, "SERVICEREGISTRY", "SISTEMA", "Fim da verificacao dos microservicos.");
	}

	private boolean isServiceOnline(String hostname, Integer port, String context) throws InvalidResponseException {
		CommunicationFacade facade = new CommunicationFacadeBean();
		facade.get("http://" + hostname + ":" + port, context + "/rest/health/status");
		return true;
	}
}