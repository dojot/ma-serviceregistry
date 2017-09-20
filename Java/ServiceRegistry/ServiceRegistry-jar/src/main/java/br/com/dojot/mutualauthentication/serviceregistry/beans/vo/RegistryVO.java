package br.com.dojot.mutualauthentication.serviceregistry.beans.vo;

import java.io.Serializable;
import java.util.Date;

public class RegistryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 512684675921835068L;
	
	private String microservice;
	
	private String version;

	private String context;

	private String hostname;
	
	private Integer port;
	
	private Date date;

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

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
