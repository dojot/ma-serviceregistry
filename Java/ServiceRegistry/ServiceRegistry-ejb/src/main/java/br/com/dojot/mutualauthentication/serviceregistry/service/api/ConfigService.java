package br.com.dojot.mutualauthentication.serviceregistry.service.api;

import javax.ejb.Local;

@Local
public interface ConfigService {
	
	String[] findCassandraContactPoints();
}