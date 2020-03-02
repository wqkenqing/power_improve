package job;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import operate.HbaseOperate;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import util.CommonUtil;
import util.KafkaUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/10/11
 * @desc
 */
@Data
@Slf4j
public class TopicToConsumer implements Runnable {
    private KafkaConsumer<String, String> consumer;
    private List<String> tlist;
    private HbaseOperate operate;
    private String consumerName;
    private Long end;

    @Override
    public void run() {
        try {
            long now = System.currentTimeMillis();
            log.info("there is thread start ,name is [{}]", Thread.currentThread().getName());
            String[] tnames = tlist.toArray(new String[tlist.size()]);
            int i = 0;
            KafkaConsumer<String, String> consumer = KafkaUtil.createConsumer("", getConsumerName());
            while (true) {
                List<Put> putList = new ArrayList<>();
                consumer.subscribe(Collections.singleton(tnames[i]));
                int count = 1;
                while (now < end) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                    long lastOffset = 0;
                    for (ConsumerRecord<String, String> record : records.records(tnames[i])) {
                        String res = record.value();
                        count++;
                        if (count % 100 == 0) {
                            log.info("线程[{}]的[{}]消费中...", Thread.currentThread().getName(), getConsumerName());
                        }
                    }
                    now = System.currentTimeMillis();
                }
                now = System.currentTimeMillis();
                setEnd(TopicUtil.getEndTime(now));
                consumer.unsubscribe();
                if (i == tnames.length - 1) {
                    i = 0;
                    log.info("consumer重置中....");
                    log.info("该consumer消费topics[{}]", Arrays.toString(tlist.toArray()));
                    consumer.close();
                    consumer = KafkaUtil.createConsumer("", getConsumerName());
                    ;
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
