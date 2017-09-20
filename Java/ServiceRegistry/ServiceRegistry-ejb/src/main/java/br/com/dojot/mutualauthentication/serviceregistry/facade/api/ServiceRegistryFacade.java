package br.com.dojot.mutualauthentication.serviceregistry.facade.api;

import javax.ejb.Local;

import br.com.dojot.mutualauthentication.serviceregistry.beans.to.RegistryTO;
import br.com.dojot.mutualauthentication.serviceregistry.beans.to.ServiceTO;

@Local
public interface ServiceRegistryFacade {
	
	boolean register(RegistryTO to);
	
	boolean unregister(RegistryTO to);
	
	ServiceTO findService(String microservice, String version, String restful, String method);

	void checkServices();
}
