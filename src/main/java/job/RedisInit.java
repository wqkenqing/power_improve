package job;

import enums.GatherLogEnum;
import redis.clients.jedis.Jedis;
import util.CommonUtil;
import util.RedisUtil;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/12/6
 * @desc
 */
public class RedisInit {
    public static void main(String[] args) {
        Jedis redisTemplate = RedisUtil.getJedis();

        //以下内容同步后,业务可正常启动,并且不用设置过期时间
        redisTemplate.hset((GatherLogEnum.LYDSJ_GATHER_CALL_ERROR_TOTAL.getName()), "key1", "0");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_LOG_COUNT_TOTAL.getName(), "key1", "0");
        redisTemplate.hset(GatherLogEnum.createDay(GatherLogEnum.LYDSJ_GATHER_LOG_STORE.getName()), "key1", "0");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_HOST_IP.getName(), "datanode1", "192.168.10.101");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_HOST_IP.getName(), "datanode2", "192.168.10.102");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_HOST_IP.getName(), "datanode3", "192.168.10.103");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_HOST_IP.getName(), "namenode2", "192.168.10.104");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_TYPE_MAP.getName(), "travel_hardware", "7");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_TYPE_MAP.getName(), "yidong", "17");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_TYPE_MAP.getName(), "industrial", "16");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_SOURCE_MAP.getName(), "industrial", "5");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_SOURCE_MAP.getName(), "yd", "8");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_BASE_TYPE_MAP.getName(), "pba", "1");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_BASE_TYPE_MAP.getName(), "pbu", "2");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_BASE_TYPE_MAP.getName(), "pto", "3");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_INIT_JOB.getName(), "store", "undo");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_INIT_JOB.getName(), "es_tmp", "undo");
        redisTemplate.hset(GatherLogEnum.LYDSJ_GATHER_DATA_INIT_JOB.getName(), "es_tmp", "undo");


        /**
         * LYDSJ_GATHER_CALL_ERROR_TOTAL
         * LYDSJ_GATHER_CALL_COUNT_TOTAL
         * LYDSJ_GATHER_CALL_COUNT
         * LYDSJ_GATHER_LOG_LOCATION
         * LYDSJ_GATHER_LOG_STATE_TIME
         * LYDSJ_GATHER_LOG_STORE_AFTERDAY
         * LYDSJ_GATHER_LOG_STORE_TOTAL
         * */

    }
}
