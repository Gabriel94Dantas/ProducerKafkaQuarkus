package org.acme.producers;

import org.acme.contexts.KafkaContext;
import org.acme.converters.EventConverter;
import org.acme.models.Event;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Closeable;
import java.io.IOException;

public class EventProducer implements Closeable {

    private final KafkaProducer<String, String> producer;
    private KafkaContext kafkaContext;

    public EventProducer() {
        this.kafkaContext = new KafkaContext();
        this.producer = new KafkaProducer<String, String>(this.kafkaContext.kafkaProperties());
    }

    public void send(Event event){
        ProducerRecord<String, String> record = new ProducerRecord<>(event.getType(),
                event.getId(),
                EventConverter.eventToJson(event));
        producer.send(record, (recordData, e) -> {
            if(e != null){
                e.printStackTrace();
            }else{
                System.out.println("Message string = " + record.value() +
                        ", Offset = " + recordData.offset());
            }
        });
    }

    @Override
    public void close() throws IOException {
        this.producer.close();
    }
}
