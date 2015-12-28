package de.lanalda.suff.curve.net;
import java.net.URI;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.sun.net.httpserver.HttpServer;

public class Main {
	
	public static void main(String[] args) {
		ResourceConfig rc = new ResourceConfig().packages("de.lanalda.suff.curve.net");
		HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create( "http://localhost:8080/" ), rc);
	}

}
