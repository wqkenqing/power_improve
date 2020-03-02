import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import util.KafkaUtil;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/11/1
 * @desc
 */
public class KafkaTest {

    @Test
    public void test() {
        KafkaConsumer consumer = KafkaUtil.createConsumer("", "test_topic2");
        ConsumerRecords<String, String> records = consumer.poll(1000);
        while (!records.isEmpty()) {
            System.out.println(records.records("test_topic2"));
        }
    }
}
