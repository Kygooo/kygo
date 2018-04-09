package com.kygo.http.exception;


public class HttpConnectHostException extends HttpException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public HttpConnectHostException(String url, String message){
		super(message);
		this.url = url;
	}
	
	

}
