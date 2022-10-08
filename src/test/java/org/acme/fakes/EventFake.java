package org.acme.fakes;


import com.google.gson.JsonObject;
import org.acme.converters.DateConverter;
import org.acme.models.Event;

import java.util.Date;
import java.util.UUID;

public class EventFake {


    public static Event mockedEvent(){
        Event event = new Event();
        UUID guid = UUID.randomUUID();
        event.setId(guid.toString());
        event.setSource("/unitTest");
        event.setTime(DateConverter.dateToString(new Date()));
        event.setSubject("Event of unit test");
        event.setCorrelationId(null);
        event.setSpecVersion("1.0");
        event.setDataContentType("application/json");
        event.setType("org.example.chiefs");
        event.setData(mockedJsonData());
        return event;
    }

    public static JsonObject mockedJsonData(){
        JsonObject jsonObject = new JsonObject();
        UUID guid = UUID.randomUUID();
        jsonObject.addProperty("pocType", "carried_state_transfer");
        jsonObject.add("cliente", mockedClientJson());
        jsonObject.addProperty("escolhaEnvio", "whatsapp");
        jsonObject.addProperty("canalSolicitante", "CANAL QUALQUER");
        jsonObject.addProperty("idTransacao", guid.toString());

        return jsonObject;
    }

    public static JsonObject mockedClientJson(){
        JsonObject jsonObject = new JsonObject();
        UUID guid = UUID.randomUUID();
        jsonObject.addProperty("idCliente", guid.toString());
        jsonObject.addProperty("nomeCliente", "TESTE2");
        jsonObject.addProperty("documentoIdentificacao", "123.456.789-10");
        jsonObject.addProperty("celular", "1195956595");
        jsonObject.addProperty("email", "email@email.com");

        return jsonObject;
    }

}
