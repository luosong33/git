package org.jumao.bi.utis.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by kartty on 2017/5/8.
 */
public class DateTotalComp implements Comparator<Map.Entry<String, Long>> {

    /**
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
        try {
            int t1 = Integer.parseInt(o1.getKey());
            int t2 = Integer.parseInt(o2.getKey());
            if (t2 > t1) {
                return -1;
            } else if (t2 == t1) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
