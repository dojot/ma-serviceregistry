package br.com.dojot.mutualauthentication.serviceregistry.beans.to;

import java.io.Serializable;

public class RegisteredTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1038590286474849078L;
	
	public RegisteredTO(Boolean result) {
		super();
		this.result = result;
	}

	private Boolean result;

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
}
