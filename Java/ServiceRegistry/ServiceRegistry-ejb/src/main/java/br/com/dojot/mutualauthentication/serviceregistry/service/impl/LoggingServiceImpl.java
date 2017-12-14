package br.com.dojot.mutualauthentication.serviceregistry.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.dojot.mutualauthentication.communication.facade.api.CommunicationFacade;
import br.com.dojot.mutualauthentication.communication.facade.impl.CommunicationFacadeBean;
import br.com.dojot.mutualauthentication.serviceregistry.beans.dto.LoggingDTO;
//import br.com.dojot.mutualauthentication.serviceregistry.messaging.api.LoggingProcessingProducerService;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.LoggingService;

@Stateless
public class LoggingServiceImpl implements LoggingService {
	
	//@EJB
	//private LoggingProcessingProducerService loggingProcessingProducerService; 

	@Override
	public void saveLogging(Level level, String component, String username, String details) {
//		CommunicationFacade facade = new CommunicationFacadeBean();
//		LoggingDTO dto = new LoggingDTO();
//		dto.setComponent(component);
//		dto.setDetails(details);
//		dto.setLevel(level.toString());
//		dto.setUsername(username);
//		dto.setNode((String)facade.requestNodeConfigs().get("node"));
//		loggingProcessingProducerService.produce(dto);
		System.out.printf("[%s] %s. %s. %s.\n", level.toString(), component, username, details);

	}	
	
}
