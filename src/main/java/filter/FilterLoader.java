package filter;

import java.util.List;
import java.util.Set;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/9/17
 * @desc filter加载器
 */
public class FilterLoader {
    public List<String> loader(TableFilter filter, List<String> tlist) {
        return filter.filterList(tlist);
    }

    public List<String> loader(TableFilter filter, Set<String> tset) {
        return filter.filterList(tset);
    }

    public boolean loader(TableFilter filter, String tname) {
        return filter.filter(tname);
    }
}
