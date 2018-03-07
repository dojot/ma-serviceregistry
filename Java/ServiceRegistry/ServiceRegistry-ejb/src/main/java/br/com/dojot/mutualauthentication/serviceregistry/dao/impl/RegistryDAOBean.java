package br.com.dojot.mutualauthentication.serviceregistry.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;

import br.com.dojot.mutualauthentication.serviceregistry.beans.vo.RegistryVO;
import br.com.dojot.mutualauthentication.serviceregistry.dao.api.RegistryDAO;
import br.com.dojot.mutualauthentication.serviceregistry.service.api.ConfigService;

@Singleton
public class RegistryDAOBean implements RegistryDAO {

	@EJB
	private ConfigService configService;

	@PostConstruct
	public void init() {
	}

	@PreDestroy
	public void close() {
	}

	@Override
	public void add(RegistryVO vo) {
	}

	@Override
	public void remove(String microservice, String version, String hostname) {
	}

	@Override
	public List<RegistryVO> search(String microservice, String version) {
		List<RegistryVO> registers = new ArrayList<RegistryVO>();
		return registers;
	}

	@Override
	public List<RegistryVO> search() {
		List<RegistryVO> registers = new ArrayList<RegistryVO>();
		return registers;
	}

}
