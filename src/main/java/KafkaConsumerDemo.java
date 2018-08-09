import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) {

        Properties properties = new Properties();
        // Bootstrap Server
        properties.setProperty("bootstrap.servers", "192.168.99.100:9092"); // 9092: Kafka Broker Port
        // 2181: Zookeeper Port
        // Check URL # http://192.168.99.100:3030/ for more PORT details
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        // Producer acks
        properties.setProperty("group.id", "test");
//      properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("enable.auto.commit", "false");
//        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("auto.offset.reset", "earliest");
        // Create Consumer
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        kafkaConsumer.subscribe(Arrays.asList("thirdtopic"));
        // Start PoolING
        while (true) {
            // Create consumer records Collection
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            // Loop through the collection
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                //consumerRecord.value();
                //consumerRecord.key();
                //consumerRecord.offset();
                //consumerRecord.partition();
                //consumerRecord.topic();
                //consumerRecord.timestamp();

                System.out.println("Partition" + consumerRecord.partition() +
                        ",  Key" + consumerRecord.key() +
                        ",  Value" + consumerRecord.value() +
                        ",  Offset" + consumerRecord.partition());
            } // End of For
            kafkaConsumer.commitSync();
        } // End of While
    } // End of main
} // End of Class
