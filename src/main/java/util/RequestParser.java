package util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Model;
import model.User;
import webserver.RequestHandler;

public class RequestParser {

	private String method;
	private String url;
	private Model model;
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	
	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}
	
	public Model getModel() {
		return model;
	}	

	public RequestParser(String method, String url) {
		// TODO Auto-generated constructor stub
		this.method = method;
		this.url = url;
	}

	public RequestParser(String method, String url, Model model) {
		// TODO Auto-generated constructor stub
		this.method = method;
		this.url = url;
		this.model = model;
	}
	

	public static RequestParser create(String headerToken) {
		// TODO Auto-generated method stub
		String[] split = headerToken.split(" ");
		String method = split[0];
		String url = split[1];
		int index = url.indexOf("?");
		
		if(index > 0){
			String path = url.substring(0, index);
			String params = url.substring(index+1);
			
			Map<String,String> map = HttpRequestUtils.parseQueryString(params);
			
			Model user = new User(
					map.get("userId"),
					map.get("password"),
					map.get("name"),
					map.get("email")
			);
			
			return new RequestParser(method, path, user);
		} else {
			return new RequestParser(method, url);
		}
		
	}

	@Override
	public String toString() {
		return "RequestParser [method=" + method + ", url=" + url + ", model=" + model + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestParser other = (RequestParser) obj;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	

	
}
