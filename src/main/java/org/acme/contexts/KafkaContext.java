package org.acme.contexts;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaContext {

    private String getKafkaBootStrapServer(){
        if (System.getenv("BROKER_HOST") != null
                && !System.getenv("BROKER_HOST").isEmpty()){
            return System.getenv("BROKER_HOST");
        }else{
            return "localhost:9092";
        }
    }

    public Properties kafkaProperties(){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getKafkaBootStrapServer());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        return properties;
    }

}
