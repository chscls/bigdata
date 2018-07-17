package com.ch.kafaka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerTest extends Thread {

    private final KafkaProducer producer;

    public ProducerTest(){
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("bootstrap.servers", "11.11.11.110:9092");
        this.producer = new KafkaProducer(props);
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo;
            System.out.println("Send:" + messageStr);
            producer.send(new ProducerRecord("my_test", messageStr));
            messageNo++;
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

