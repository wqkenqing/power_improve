package util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019-08-06
 */
@Slf4j
public class PropertyUtil {

    public static Properties initConfig(String path) {
        ClassLoader classLoader = CommonUtil.class.getClassLoader();
        Properties properties = new Properties();
        InputStream in = classLoader.getResourceAsStream(path);
        try {
            properties.load(in);

        } catch (IOException e) {
            log.warn("配置文件[{}]加载失败", path);
        }

        return properties;
    }

}
