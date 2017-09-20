package br.com.dojot.mutualauthentication.serviceregistry.messaging.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import br.com.dojot.mutualauthentication.communication.constants.CommunicationKeysConstants;
import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.serviceregistry.beans.dto.LoggingDTO;
import br.com.dojot.mutualauthentication.serviceregistry.dao.api.ConfigDAO;
import br.com.dojot.mutualauthentication.serviceregistry.messaging.api.LoggingProcessingProducerService;
import br.com.dojot.mutualauthentication.serviceregistry.utils.ServiceRegistryConstants;


@Startup
@Singleton
public class LoggingProcessingProducerServiceImpl extends Thread implements LoggingProcessingProducerService {
	private ProducerServiceImpl producer;
	
	@EJB
	private ConfigDAO configDAO;
    
	@PostConstruct
	public void init() {
		CommunicationFacade facade = new CommunicationFacadeBean();
		producer = new ProducerServiceImpl(configDAO.get(ServiceRegistryConstants.PARAM_KAFKA_BOOTSTRAP_SERVERS),
				ServiceRegistryConstants.TOPIC_LOGGING_PROCESSING,
				(String) facade.requestNodeConfigs().get(CommunicationKeysConstants.KEY_VERSION), "sr.logprocessing.");
	}
	
	@PreDestroy
	public void close() {
		producer.close();
	}

	@Override
	public void produce(LoggingDTO dto) {
		producer.produce(dto);
	}

}