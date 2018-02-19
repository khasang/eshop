package io.khasang.eshop.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RegistrationOnFlight {
    @WebMethod(operationName = "RegistrationOnFlight")
    String registration(String name);
}
