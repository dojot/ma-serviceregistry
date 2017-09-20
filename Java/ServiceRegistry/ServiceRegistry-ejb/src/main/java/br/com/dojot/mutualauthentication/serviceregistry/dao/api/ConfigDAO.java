package br.com.dojot.mutualauthentication.serviceregistry.dao.api;

import java.util.List;

public interface ConfigDAO {
	
	List<String> searchCassandraContactPoints();

	String get(String param);
}
