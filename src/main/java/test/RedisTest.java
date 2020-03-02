package test;

import enums.GatherLogEnum;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import util.CommonUtil;
import util.RedisUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2020/1/8
 * @desc
 */
public class RedisTest {
    Jedis jedis = RedisUtil.getJedis();

    @Test
    public void showRedisKey() {
        Set<String> kset = jedis.keys("*");
        kset.forEach(key -> {
            System.out.printf(key);
        });
    }

    @Test
    public void createRedisKey() throws FileNotFoundException {
        String path = "/Users/kuiq.wang/Desktop/doc/redis_key.txt";
        String keys = CommonUtil.stream2String(new FileInputStream(path), "utf8");
        String[] ks = keys.split("\n");
        for (String k : ks) {
            if (!jedis.exists(k)) {
                jedis.hset("key0", "val", "0");
            }
        }
    }

    @Test
    public void transform() {
        Jedis jedis1 = new Jedis("datanode1", 6379);
        jedis1.auth("test123");
        String keys = "LYDSJ_DATA_BASE_TYPE\n" +
                "LYDSJ_DATA_SOURCE\n" +
                "LYDSJ_GATHER_DATA_BASE_TYPE_MAP\n" +
                "LYDSJ_GATHER_DATA_INIT_JOB\n" +
                "LYDSJ_GATHER_DATA_SOURCE_MAP\n" +
                "LYDSJ_GATHER_DATA_TYPE_MAP\n" +
                "LYDSJ_GATHER_HOST_IP\n" +
                "LYDSJ_GATHER_DATA_INIT_JOB";
        for (String key : keys.split("\n")) {

            Map<String, String> kmap = jedis1.hgetAll(key);
            kmap.keySet().forEach(k -> {
                if (kmap.get(k) != null) {
                    jedis.hset(key, k, kmap.get(k));
                }
            });
        }

    }

    @Test
    public void createKey() {
        jedis.hset("LYDSJ_SAHRE_COUNT_TOTAL", "weather_burea_data", "1");
        jedis.hset("LYDSJ_SAHRE_COUNT_TOTAL", "environment_bureau-data", "609");
        jedis.hset("LYDSJ_SAHRE_STORE_TOTAL", "environment_bureau-data", "59743");
        jedis.hset("LYDSJ_SAHRE_COUNT_TOTAL", "weather_burea_data", "143");
        jedis.hset(GatherLogEnum.createDay("LYDSJ_SAHRE_ERROR_COUNT"), "weather_burea_data", "0");
        jedis.hset(GatherLogEnum.createDay("LYDSJ_SAHRE_ERROR_COUNT"), "environment_bureau-data", "0");
        jedis.hset(GatherLogEnum.createDay("LYDSJ_SAHRE_JOIN_COUNT"), "weather_burea_data", "1");
        jedis.hset(GatherLogEnum.createDay("LYDSJ_SAHRE_JOIN_COUNT"), "environment_bureau-data", "1");
    }
}
