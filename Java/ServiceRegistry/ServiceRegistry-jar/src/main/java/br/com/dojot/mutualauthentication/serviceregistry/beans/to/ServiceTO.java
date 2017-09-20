package br.com.dojot.mutualauthentication.serviceregistry.beans.to;

import java.io.Serializable;

public class ServiceTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1848254835245980668L;
	
	private String microservice;
	
	private String version;
	
	private String restful;
	
	private String method;

	private String target;
	
	private String path;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRestful() {
		return restful;
	}

	public void setRestful(String restful) {
		this.restful = restful;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMicroservice() {
		return microservice;
	}

	public void setMicroservice(String microservice) {
		this.microservice = microservice;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


}
