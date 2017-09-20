package br.com.dojot.mutualauthentication.serviceregistry.dao.api;

import java.util.List;

import br.com.dojot.mutualauthentication.serviceregistry.beans.vo.RegistryVO;

public interface RegistryDAO {

	void add(RegistryVO vo);
	
	void remove(String microservice, String version, String hostname);
	
	List<RegistryVO> search(String microservice, String version);
	
	List<RegistryVO> search();
}
