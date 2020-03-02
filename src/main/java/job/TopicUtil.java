package job;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/11/5
 * @desc
 */
public class TopicUtil {

    public static String topicToTable(String topic) {
        String pre = "";
        String pren = "";
        if (topic.contains("-")) {
            String[] topics = topic.split("-");
            if (topics.length > 2) {
                pre = topics[0];
                pren = pre + ":";
                pre = pre + "-";
            }
        }
        if (StringUtils.isNotBlank(pre)) {
            topic = topic.replace(pre, pren);
        }
        return topic;
    }

    public static Long getEndTime(Long now) {
        return now + 1000 * 30;
    }

    public static String numberSolve(String size) {
        String number = "";
        String unit = "";
        if (size.contains(".")) {
            String[] pres = size.split("\\.");
            String pre = pres[0];
            String endNumber = pres[1].replaceAll("[^(0-9)]", "");
            unit = size.replaceAll("[^(a-zA-Z)]", "");
            number = pre + "." + endNumber;
        } else {
            number = size.replaceAll("[^(0-9)]", "");
            unit = size.replaceAll("[^(0-9)]", "");
        }
        long num = 0;
        if (unit.contains("m")) {
            num = 1024 * 1024;
        }
        if (unit.contains("k")) {
            num = 1024;
        }
        if (unit.contains("g")) {
            num = 1024 * 1024 * 1024;
        }
        Double res = Double.valueOf(number) * Double.valueOf(num) * 10;

        BigDecimal bigDecimal = new BigDecimal(res);
        DecimalFormat fmt = new DecimalFormat("#");
        String result = fmt.format(bigDecimal.doubleValue());
        return result;
    }

    public static void main(String[] args) {
        String res = numberSolve("13.6mb");
        System.out.println(res);
//        double ll = 137678028.800000011920928955078125;
//        DecimalFormat format = new DecimalFormat("#");
//        System.out.println(format.format(ll));
    }
}
