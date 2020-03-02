package job;

import com.google.inject.internal.cglib.core.$TypeUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import util.KafkaUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/12/18
 * @desc
 */
public class KafkaProducerJob {
    KafkaProducer<String, String> producer = KafkaUtil.createProducer();
    public boolean checkTopic(String tname) {
        if (KafkaUtil.topicIsExist(tname)) {
            return false;
        }
        return true;
    }

    public void sendMessage(String tname, String val) {

        producer.send(new ProducerRecord<String, String>(tname, val));

    }

    public static void main(String[] args) throws InterruptedException {
        KafkaProducerJob job = new KafkaProducerJob();
        while (true) {
            for (int i = 0; i < 25; i++) {
                String topicname = "topic";
                topicname = topicname + i;
                if (job.checkTopic(topicname)) {
                    KafkaUtil.createTopic(topicname);
                }
                for (int j = 0; j < 100; j++) {
                    String val = "aldkjfaldjaldfja";
                    job.sendMessage(topicname, val);
                }
            }
            TimeUnit.MINUTES.sleep(3);
        }
    }
}
