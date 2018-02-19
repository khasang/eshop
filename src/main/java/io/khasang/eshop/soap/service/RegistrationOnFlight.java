package io.khasang.eshop.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://127.0.0.1:12345/Service")
public interface RegistrationOnFlight {
    @WebMethod(operationName = "RegistrationOnFlight")
    String registration(String name);
}
