public class KafkaConsumerProducerDemo {

    public static void main(String[] args) {
        new ProducerTest().start();
        new Consumer().start();
    }

}