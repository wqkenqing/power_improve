package util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/10/22
 * @desc
 */
@Slf4j
public class RedisUtil {
    static Jedis jedis;
    static Properties props;

    static {
        props = PropertyUtil.initConfig("redis.props");
        //连接redis服务器(在这里是连接本地的)
        jedis = new Jedis((String) props.get("host"), 6379);
        //权限认证
        jedis.auth((String) props.get("auth"));
    }

    public static Jedis getJedis() {

        //连接redis服务器(在这里是连接本地的)
        Jedis jedisn = new Jedis((String) props.get("host"), 6379);
        //权限认证
        jedisn.auth((String) props.get("auth"));
        return jedisn;
    }

    public static void showAll(int index) {
        jedis.select(index);
        Set<String> kset = jedis.keys("*");
        for (String s : kset) {
            System.out.println(s);
            System.out.println(JSONObject.toJSONString(jedis.get(s)));
        }
    }

    public static void changeStatus() {
        jedis.select(0);
        jedis.set("day_init_job", "do");
    }

    public static void putMap() {
        Map<String, String> map = new HashMap<>();
        map.put("data1", "val2");
        map.put("data2", "val2");
        map.put("data3", "val2");
        map.put("data4", "val2");
        map.put("data5", "val2");
        jedis.hmset("state_all", map);
    }

    public static void getMap(String key) {
        Map<String, String> kmap = jedis.hgetAll(key);
        Set<String> kset = kmap.keySet();
        for (String k : kset) {
            String res = kmap.get(k);
            System.out.print(k + " ");
            System.out.println(res);
        }
    }

    public static void main(String[] args) {

    }
}
