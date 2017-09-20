package br.com.dojot.mutualauthentication.serviceregistry.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.dojot.mutualauthentication.serviceregistry.beans.to.RegistryTO;
import br.com.dojot.mutualauthentication.serviceregistry.beans.to.ServiceTO;
import br.com.dojot.mutualauthentication.serviceregistry.facade.api.ServiceRegistryFacade;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.RegistryService;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServiceRegistryFacadeBean implements ServiceRegistryFacade {
	
	@EJB
	private RegistryService registryService;

	@Override
	public boolean register(RegistryTO to) {		
		return registryService.register(to);
	}

	@Override
	public boolean unregister(RegistryTO to) {
		return registryService.unregister(to);
	}

	@Override
	public ServiceTO findService(String microservice, String version, String restful, String method) {
		return registryService.findService(microservice, version, restful, method);
	}

	@Override
	public void checkServices() {
		registryService.checkServices();
	}
	
}
