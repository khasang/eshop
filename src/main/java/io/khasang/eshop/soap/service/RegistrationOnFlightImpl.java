package io.khasang.eshop.soap.service;

import javax.jws.WebService;

@WebService(endpointInterface = "io.khasang.eshop.soap.service.RegistrationOnFlight")
public class RegistrationOnFlightImpl implements RegistrationOnFlight {
    private final static String str = (", you are registrated on aplane Moscow - Anewhere!");

    @Override
    public String registration(String name) {
        return name + str;
    }
}
