package operate;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import util.CommonUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wqkenqing
 * @emai wqkenqingto@163.com
 * @time 2019/11/19
 * @desc
 */
public class CountNum {
    @Deprecated
    public void count(String path) throws FileNotFoundException, ParseException {
        String file = CommonUtil.stream2String(new FileInputStream(path), "utf8");
        String[] files = file.split("\n");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stime = "2019-11-17 19:00:00";

        String etime = "2019-11-18 19:00:00";
        int sum = 0;
        for (String f : files) {

            int start = f.indexOf("[", f.indexOf("[") + 1);
            int end = f.indexOf("]", f.indexOf("]") + 1);
            String time = f.substring(start + 1, end);
            if (format.parse(time).getTime() <= format.parse(etime).getTime() && format.parse(time).getTime() >= format.parse(stime).getTime()) {
                start = f.lastIndexOf("[");
                end = f.lastIndexOf("]");
                String count = f.substring(start + 1, end);
                sum = Integer.parseInt(count) + sum;
            }
        }
        System.out.println("结果为:" + sum);
    }

    public void countInfo(String path) throws FileNotFoundException, ParseException {
        String file = CommonUtil.stream2String(new FileInputStream(path), "utf8");
        String[] files = file.split("\n");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stime = "2019-11-17 19:00:00";
        String etime = "2019-11-18 19:02:00";
        int count = 0;
        int count1 = 0;
        List<Integer> flist = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (String f : files) {
            count1++;
            if (count1 % 100000 == 0) {
                System.out.println("执行了" + count1);
            }
            JSONObject jobj = JSONObject.parseObject(f);
            String time = "";
            if (jobj.get("ModifyTime") != null) {
//                time = (String) jobj.get("ModifyTime");
                time = (String) jobj.get("PassTime");
            }
            if (jobj.get("modifyTime") != null) {
//                time = (String) jobj.get("modifyTime");
                time = (String) jobj.get("passTime");
            }

            if (StringUtils.isNotEmpty(time)) {
                if (format.parse(time).getTime() <= format.parse(etime).getTime() && format.parse(time).getTime() >= format.parse(stime).getTime()) {
                    if (jobj.get("FID") != null) {
                        int fid = (int) jobj.get("FID");
                        if (flist.contains(fid)) {
                            if (map.get(fid) != null) {
                                map.put(fid, map.get(fid) + 1);
                            } else {
                                map.put(fid, 2);
                            }
                        } else {
                            flist.add(fid);
                        }
                    }
                    if (jobj.get("fID") != null) {
                        int fid = (int) jobj.get("fID");
                        if (flist.contains(fid)) {
                            if (map.get(fid) != null) {
                                map.put(fid, map.get(fid) + 1);
                            } else {
                                map.put(fid, 2);
                            }
                        } else {
                            flist.add(fid);
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println(count);
        System.out.println(JSONObject.toJSONString(map));
        System.out.println(map.size());

    }

    public static void main(String[] args) throws FileNotFoundException, ParseException {
        CountNum countNum = new CountNum();
        countNum.countInfo("/Users/wqkenqing/Desktop/allinfo.txt");
//        countNum.count("/Users/wqkenqing/Desktop/upload.txt");

    }

}

