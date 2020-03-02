package filter;

import java.util.List;
import java.util.Set;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/9/17
 * @desc
 */
public interface TableFilter {
    /**
     * 加载相关配置
     *
     * @return void
     * @throws
     * @author wqkenqing
     * @date 2019/9/17
     **/
    List<String> init();

    /**
     * 方法的功能描述
     *
     * @param tname
     * @return
     * @throws
     * @date 2019/9/17
     **/
    boolean filter(String tname);

    List<String> filterList(List<String> tlist);

    /**
     * 方法的功能描述
     *
     * @param tset
     * @return List<String>
     * @throws
     * @author wqkenqing
     * @date 2019/9/17
     **/
    List<String> filterList(Set<String> tset);


}
