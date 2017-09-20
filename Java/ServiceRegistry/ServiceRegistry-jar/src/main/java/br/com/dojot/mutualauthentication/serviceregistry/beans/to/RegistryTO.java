package br.com.dojot.mutualauthentication.serviceregistry.beans.to;

import java.io.Serializable;

public class RegistryTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1960473131792652683L;

	private String hostname;
	
	private String port;
	
	private String node;
	
	private String microservice;
	
	private String version;
	
	private String context;

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
