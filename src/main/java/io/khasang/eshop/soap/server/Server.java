package io.khasang.eshop.soap.server;

import io.khasang.eshop.soap.service.RegistrationOnFlightImpl;

import javax.xml.ws.Endpoint;

public class Server {
    private final static String ADDRES = "http://127.0.0.1:12345/Service";

    protected Server() {
        System.out.println("Server starting...");
        Object implementation = new RegistrationOnFlightImpl();
        Endpoint.publish(ADDRES, implementation);
    }

    public static void main(String[] args) {
        new Server();
        System.out.println("Server listening ws: " + ADDRES);
        System.out.println("WSDL hosted ws: " + ADDRES + "?wsdl");
    }
}
