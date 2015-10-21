package util;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

public class RequestParserTest {

	@Test
	public void test() {
		RequestParser rp = RequestParser.create("GET /index.html HTTP/1.1");
		assertEquals(new RequestParser("GET","/index.html"), rp);
	}
	
	@Test
	public void test_QueryString() throws Exception {
		RequestParser rp = RequestParser.create("GET /create?userId=mixed&password=pw&name=yongwoo&email=mixed%40mixed.net HTTP/1.1");
		assertEquals(new RequestParser("GET","/create",new User("mixed","pw","yongwoo","mixed%40mixed.net")), rp);
	}

}
