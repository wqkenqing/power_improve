package command;

import lombok.extern.slf4j.Slf4j;
import util.KafkaUtil;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/10/18
 * @desc
 */
@Slf4j
public class KafkaCommand {
    static String zookeeperStr = "namenode:2181,datanode1:2181,datanode2:2181";
    public static void main(String[] args) {
            String operate = args[0];
            String topicName = args[1];
            String zaddress = args[2];
        log.info("[{}]进入了", topicName);

        if (operate.equals("create")) {
               KafkaUtil.createTopic(topicName);
            }
            if (operate.equals("delete")) {
                KafkaUtil.deleteTopic(topicName);
            }
            if (operate.equals("exist")) {
                KafkaUtil.topicIsExist(topicName);
            }
            if (operate.equals("show")) {
                KafkaUtil.getTopicListShow(zaddress);
            }
    }
}
