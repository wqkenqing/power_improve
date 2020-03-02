package filter;


import java.util.List;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/10/24
 * @desc
 */
public class TopicNameFilter extends BaseFilter {
    @Override
    public boolean filter(String tname) {
        List<String> flist = init();
        if (tname.contains("-")) {
            String ts[] = tname.split("-");
            String tone = ts[0];
            return flist.contains(tone);
        }
        return false;
    }
}
