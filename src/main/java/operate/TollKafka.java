package operate;

import com.alibaba.fastjson.JSONObject;
import kafka.Kafka;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import util.CommonUtil;
import util.KafkaUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/11/11
 * @desc
 */
@Slf4j
public class TollKafka {

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String path = args[0];
        String topic = args[1];
        String info = CommonUtil.stream2String(new FileInputStream(path), "utf8");
        String tag = "\n";

        String[] infos = info.split(tag);
        int count = (int) (Math.random() * 100);
        int countn = 0;
        KafkaProducer<String, String> producer = KafkaUtil.createProducer();
        int i = 0;
        while (i < infos.length - 1) {
            String inf = infos[i];
            if (topic.contains("state")) {
                String time = CommonUtil.formatDateToString(new Date());
                JSONObject job = JSONObject.parseObject(inf);
                job.put("ModifyTime", time);
                time = CommonUtil.formatDateToString(new Date());
                job.put("UpdateTime", time);
                //发送消息
                try {
                    producer.send(new ProducerRecord<String, String>(topic, JSONObject.toJSONString(job)));
                } catch (Exception e) {
                    log.error("the action for  sending  message of state has some  wrong");
                }
            } else {
                String time = CommonUtil.formatDateToString(new Date());
                JSONObject job = JSONObject.parseObject(inf);
                job.put("modifyTime", time);
                time = CommonUtil.formatDateToString(new Date());
                job.put("passTime", time);
                //发送消息
                try {
                    producer.send(new ProducerRecord<String, String>(topic, JSONObject.toJSONString(job)));
                } catch (Exception e) {
                    log.error("the action for  sending  messages of info has some  wrong");
                }
            }
            i++;
            if (i == infos.length - 1) {
                i = 0;
            }
            countn++;
            if (countn >= count) {
                log.info("this time has upload [{}] messages", count);
                countn = 0;
                count = (int) ((Math.random()) * 100 + 1);
                TimeUnit.SECONDS.sleep(30);
            }
        }

    }
}
