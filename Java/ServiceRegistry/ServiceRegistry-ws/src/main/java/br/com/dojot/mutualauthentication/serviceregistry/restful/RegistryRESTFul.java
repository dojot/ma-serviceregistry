package br.com.dojot.mutualauthentication.serviceregistry.restful;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.dojot.mutualauthentication.serviceregistry.beans.to.RegisteredTO;
import br.com.dojot.mutualauthentication.serviceregistry.beans.to.RegistryTO;
import br.com.dojot.mutualauthentication.serviceregistry.beans.to.ServiceTO;
import br.com.dojot.mutualauthentication.serviceregistry.facade.api.ServiceRegistryFacade;

@Named
@Path("/registry")
public class RegistryRESTFul {
	
	@EJB
	private ServiceRegistryFacade serviceRegistryFacade;

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(RegistryTO to) {
		boolean status = serviceRegistryFacade.register(to);
		RegisteredTO registered = new RegisteredTO(status);
		return Response.status(200).entity(registered).build();
	}

	@POST
	@Path("/unregister")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response validate(RegistryTO to) {
		boolean status = serviceRegistryFacade.unregister(to);
		return Response.status(200).entity(status).build();
	}

	@POST
	@Path("/findservice")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findService(ServiceTO to) {
		ServiceTO serviceTO = serviceRegistryFacade.findService(to.getMicroservice(), to.getVersion(), to.getRestful(), to.getMethod());
		return Response.status(200).entity(serviceTO).build();
	}
	
	@POST
	@Path("/check")
	public Response checkServices() {
		serviceRegistryFacade.checkServices();
		return Response.ok().build();
	}

}
