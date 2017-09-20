package br.com.dojot.mutualauthentication.serviceregistry.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.dojot.mutualauthentication.serviceregistry.dao.api.ConfigDAO;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.ConfigService;


@Stateless
public class ConfigServiceImpl implements ConfigService {
	
	@EJB
	private ConfigDAO configDAO;

	@Override
	public String[] findCassandraContactPoints() {
		List<String> hosts = configDAO.searchCassandraContactPoints();
		int size = hosts.size();
		String[] contactPoints = new String[size];		
		for (String host : hosts) {
			contactPoints[size-1] = host;
			size--;
		}
		return contactPoints;
	}	

}
