package util;

import operate.HbaseOperate;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/11/5
 * @desc
 */
public class KafkaTest {
    KafkaProducer<String, String> producer;
    KafkaConsumer<String, String> consumer;

    @Test
    public void sendInfo() throws InterruptedException, FileNotFoundException {
        producer = KafkaUtil.createProducer();
        String toipc = "test2";
        String trades = CommonUtil.stream2String(new FileInputStream("/Users/wqkenqing/Desktop/hy_merchant_info_view.csv"), "utf8");
        String trad[] = trades.split("\n");
        for (String t : trad) {
            producer.send(new ProducerRecord<>(toipc, t));
        }
        producer.flush();
        producer.close();
        TimeUnit.MINUTES.sleep(1);
    }

    @Test
    public void conumserInfo() throws InterruptedException {
        consumer = KafkaUtil.createConsumer("");
        List<String> tlist = new ArrayList<>();
        tlist.add("topic0");
        tlist.add("topic1");
        tlist.add("topic2");
        tlist.add("topic3");
        consumer.subscribe(Collections.singleton("logstream"));

        ConsumerRecords<String, String> records = consumer.poll(1000);
        for (ConsumerRecord<String, String> record : records.records("logstream")) {
            System.out.printf("\n\roffset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
        }

    }

    @Test
    public void createTopic() throws FileNotFoundException {
//        String toipcs = CommonUtil.stream2String(new FileInputStream("/Users/wqkenqing/Desktop/hy_merchant_info_view.csv"), "utf8");
//        for(String topic:toipcs.split("\n")){
//            KafkaUtil.createTopic(topic);
//        }
        KafkaUtil.createTopic("pba-travel_hardware-info_screen_network");

    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
//        KafkaUtil.deleteTopic("jzw_toll_island_info");KafkaUtil.deleteTopic("jzw_toll_island_info");
    }
}
