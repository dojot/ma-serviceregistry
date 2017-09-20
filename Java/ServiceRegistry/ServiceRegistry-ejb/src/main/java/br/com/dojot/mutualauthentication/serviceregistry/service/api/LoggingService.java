package br.com.dojot.mutualauthentication.serviceregistry.service.api;

import javax.ejb.Local;

@Local
public interface LoggingService {
	
	public enum Level { INFO, WARNING, ERROR };
	
	void saveLogging(Level level, String component, String username, String details);
}
