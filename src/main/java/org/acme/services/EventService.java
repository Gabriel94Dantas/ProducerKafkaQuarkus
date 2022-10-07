package org.acme.services;

import org.acme.converters.EventConverter;
import org.acme.models.Event;
import org.acme.producers.EventProducer;

import java.nio.charset.StandardCharsets;

public class EventService {

    private static final int MAX_EVENT_SIZE_KB = 65;
    private EventProducer eventProducer;

    public EventService(){
        this.eventProducer = new EventProducer();
    }

    public Boolean payloadTooLarge(String eventString){
        if(eventString != null && !eventString.isEmpty()){
            byte[] bytes = eventString.getBytes(StandardCharsets.UTF_8);
            if(bytes.length > MAX_EVENT_SIZE_KB * 1024){
                return true;
            }
        }
        return false;
    }

    public void sendEvent(String eventString){
        Event event = EventConverter.jsonToEvent(eventString);
        this.eventProducer.send(event);
    }

}
