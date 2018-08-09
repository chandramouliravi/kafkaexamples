import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerDemo {
    public static void main(String[] args) {

        Properties properties=new Properties();
         // Bootstrap Server
        properties.setProperty("bootstrap.servers","192.168.99.100:9092"); // 9092: Kafka Broker Port
                                                                           // 2181: Zookeeper Port
        // Check URL # http://192.168.99.100:3030/ for more PORT details
        properties.setProperty("key.serializer",StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
         // Producer acks
        properties.setProperty("acks","all") ;// value can be 0, 1 or all
        properties.setProperty("retries","3");
        properties.setProperty("linger.ms","1");
         // Create Producer
        Producer<String, String> producer=new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);

        for(int key=0; key<10; key++) {
            // Create Publish Record
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("thirdtopic", Integer.toString(key), "test message");
            // Publish Publish Record
            producer.send(producerRecord);
            producer.flush();  // linger.ms works as well
        }
         // Close the Producer
        producer.close();
    }
}
