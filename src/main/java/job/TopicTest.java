package job;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import filter.TableFilter;
import filter.TopicNameFilter;
import operate.HbaseOperate;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import util.KafkaUtil;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/12/17
 * @desc
 */
public class TopicTest {
    TopicToConsumer tohbase;
    List<KafkaConsumer> clist;
    ExecutorService pool;
    static final int core = 4;

    public void solveAndUpload() throws InterruptedException {
        List<String> tlist = KafkaUtil.getTopicList().stream().filter(s -> {
            return s.contains("topic");
        }).collect(Collectors.toList());
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("upload-thread-%d").build();
        pool = newFixedThreadPool(4, namedThreadFactory);

        for (int i = 1; i <= 4; i++) {
            List<String> rlist = splitTopicList(tlist, i);
            String consumerName = "conumer" + i;
            tohbase = new TopicToConsumer();
            tohbase.setConsumerName(consumerName);
            tohbase.setTlist(rlist);
            tohbase.setEnd(TopicUtil.getEndTime(System.currentTimeMillis()));
            pool.execute(tohbase);
        }
    }


    public List<String> getTopicsOnline() {
        TableFilter tfilter = new TopicNameFilter();
        List<String> tlist = KafkaUtil.getTopicList();
        return tfilter.filterList(tlist);
    }

    /**
     * 切割topicList
     */
    public static List<String> splitTopicList(List<String> tlist, int index) {
        int step = tlist.size() / core;
        List<String> rlist;
        if (index != 4) {
            int start = (index - 1) * step;
            int end = (index * step);
            rlist = tlist.subList(start, end);
        } else {
            int start = (index - 1) * step;
            rlist = tlist.subList(start, tlist.size());
        }
        return rlist;
    }

    public static void main(String[] args) {
        TopicTest upload = new TopicTest();
        try {
            upload.solveAndUpload();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
