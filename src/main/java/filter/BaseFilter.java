package filter;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/9/17
 * @desc 通过命令空间进行过滤
 */
@Slf4j
public abstract class BaseFilter implements TableFilter {
    Map map = null;
    private String path="pba,pbu,pto";

    @Override
    public List<String> init() {
        List<String> flist = new ArrayList<>();
        try {
            String val = path;
            if (val.contains(",")) {
                String[] vals = val.split(",");
                for (String v : vals) {
                    flist.add(v);
                }
            } else {
                flist.add(val);
            }
        } catch (Exception e) {
            log.warn("[{}]文件加载失败", path);
        }
        return flist;
    }

    @Override
    public abstract boolean filter(String tname);

    @Override
    public List<String> filterList(List<String> tlist) {
        List<String> rlist = new ArrayList<>();
        for (String tname : tlist) {
            if (filter(tname)) {
                rlist.add(tname);
            }
        }
        return rlist;
    }

    @Override
    public List<String> filterList(Set<String> tset) {
        List<String> rlist = new ArrayList<>();
        for (String tname : tset) {
            if (filter(tname)) {
                rlist.add(tname);
            }
        }
        return rlist;
    }
}
