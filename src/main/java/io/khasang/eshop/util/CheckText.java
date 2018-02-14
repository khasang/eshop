package io.khasang.eshop.util;

import net.yandex.speller.services.spellservice.CheckTextRequest;
import net.yandex.speller.services.spellservice.SpellServiceSoap;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class CheckText {

    private final static String ADRESS = "https://speller.yandex.net/services/spellservice?WSDL";

    public String checkWord(String text) throws MalformedURLException {
        //Путь с которым мы работаем
        URL url = new URL(ADRESS);
        //Работает с xml.namespace, с пространством имен
        QName qName = new QName("https://speller.yandex.net/services/spellservice", "SpellService");
        //Работа с SOAP сервисами
        Service service = Service.create(url, qName);
        //Подключаемся к порту данного интерфейса SOAP
        SpellServiceSoap serviceSoap = service.getPort(SpellServiceSoap.class);
        //Создаем и устанавливаем параметры интерфейса
        CheckTextRequest checkTextRequest = new CheckTextRequest();
        checkTextRequest.setText(text);
        checkTextRequest.setFormat("plain");
        checkTextRequest.setLang("eng");

        if (serviceSoap.checkText(checkTextRequest).getSpellResult().getError().size() != 0){
            return serviceSoap.checkText(checkTextRequest).getSpellResult().getError().get(0).getS().toString();
        } else {
            return "Word correct!";
        }
    }
}
