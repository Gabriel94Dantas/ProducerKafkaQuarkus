package org.acme.unit.tests;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.contexts.KafkaContext;
import org.acme.converters.EventConverter;
import org.acme.models.Event;
import org.acme.producers.EventProducer;
import org.acme.fakes.EventFake;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


@QuarkusTest
public class EventProducerTest {

    @Test
    public void testSendProducer(){
        Event event = EventFake.mockedEvent();
        EventProducer mockedEventProducer = Mockito.mock(EventProducer.class);
        KafkaProducer producer = Mockito.mock(KafkaProducer.class);


        Mockito.when(producer.send(Mockito.any(ProducerRecord.class),
                Mockito.any(Callback.class))).thenReturn(null);
        Mockito.spy(EventProducer.class).send(event);
        Mockito.verifyNoInteractions(mockedEventProducer);
    }
}