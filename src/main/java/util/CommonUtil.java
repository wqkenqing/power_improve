package util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @className:CommonUtil
 * @author:wqkenqing
 * @describe:一些常用的工具方法
 * @date:2017/8/18
 **/
@Slf4j
public class CommonUtil {
    static List<String> iplist = new ArrayList<String>();
    static int daySecond = 86400;

    public static String stream2String(InputStream in, String charset) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader r = new InputStreamReader(in, charset);
            int length = 0;
            for (char[] c = new char[1024]; (length = r.read(c)) != -1; ) {
                sb.append(c, 0, length);
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //输出对应的url
    public static void outputHtml(String listurl, String tag, String outbaseurl) throws IOException {
        String outpath = outbaseurl + tag + ".txt";
        OutputStream out = new FileOutputStream(outpath);
        out.write(listurl.getBytes());
    }


    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

    public static String StringbyMd5(String con) throws NoSuchAlgorithmException {

        byte[] btInput = con.getBytes();
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        // 使用指定的字节更新摘要
        mdInst.update(btInput);
        // 获得密文
        byte[] md = mdInst.digest();
        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    public static String mSec2hms(long mSec) {
        int ss = 1000;
        int mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = mSec / dd;
        Long hour = (mSec - day * dd) / hh;
        long minute = (mSec - day * dd - hour * hh) / mi;
        long second = (mSec - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = mSec - day * dd - hour * hh - minute * mi - second * ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond).append("毫秒");
        }
        return sb.toString();
    }

    public static Long getTimeInterval(String pre, String end) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = format.parse(pre);
        Date date2 = format.parse(end);

        return date2.getTime() - date1.getTime();
    }


    public static String formatDateToString(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Properties initConfig(String configName) {
        ClassLoader classLoader = CommonUtil.class.getClassLoader();
        Properties properties = new Properties();
        InputStream in = classLoader.getResourceAsStream(configName);
        try {

            properties.load(in);

        } catch (IOException e) {
            log.warn("配置文件[{}]加载失败", configName);
        }

        return properties;
    }


    public static String topicNameSolve(String topicName) {
        String tag = ":";
        String tag_two = "-";
        String[] topics = topicName.split(tag_two);
        String namespace = topics[0];
        String name = topicName.replace(namespace + tag_two, "");
        return namespace + tag + name;
    }

    public static String rowkeyCreated() {
        String rowkey = UUID.randomUUID().toString();
        return rowkey;
    }

    public static void setExpireTime(Jedis jedis, String key, int time) {
        jedis.expire(key, daySecond * time);
    }
//
//    private static synchronized long nextId(long epochSecond) {
//        if (epochSecond < lastEpoch) {
//            // warning: clock is turn back:
//            logger.warn("clock is back: " + epochSecond + " from previous:" + lastEpoch);
//            epochSecond = lastEpoch;
//        }
//        if (lastEpoch != epochSecond) {
//            lastEpoch = epochSecond;
//            reset();
//        }
//        offset++;
//        long next = offset & MAX_NEXT;
//        if (next == 0) {
//            logger.warn("maximum id reached in 1 second in epoch: " + epochSecond);
//            return nextId(epochSecond + 1);
//        }
//        return generateId(epochSecond, next, SHARD_ID);
//        $SourceProvider
//    }
}