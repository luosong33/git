package cn.newtouch.dcpp.console.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by luosong on 2017/4/24.
 */
public class ImpalaRusultUtil {

    public void setColumn(Object obj,HashMap<String,String> map){
        while (true){
            Set set = map.keySet();
            Iterator it = set.iterator();
            while (it.hasNext()){
                System.out.println(map.get(it.next()));
            }
        }
    }
}
