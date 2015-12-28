package de.lanalda.suff.curve.net;
import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;

public class Main {
	
	public static void main(String[] args) {
		String[] s = new String[1];
		s[0] = "de.lanalda.suff.curve.net";
		ResourceConfig rc = new ResourceConfig().packages(s);
		HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create("http://localhost:8080/"), rc);
	}

}
