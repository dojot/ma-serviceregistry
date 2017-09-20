package br.com.dojot.mutualauthentication.serviceregistry.messaging.api;

import br.com.dojot.mutualauthentication.serviceregistry.beans.dto.LoggingDTO;

public interface LoggingProcessingProducerService {
	
	void produce(LoggingDTO dto);
}
