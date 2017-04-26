package com.jumore.jmbi.common.util.export;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * MAP转bean 及 bean 转MAP
 * @author huangwq
 *
 */
public class BeanUtil {

	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	public static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					Object value = map.get(key);
					// 得到property对应的setter方法
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}
	public static Map<String, Object> transBean2Map(Object obj, Map<String, Object> params) {
    if (obj == null) {
      return null;
    }
    if (params == null) {
      params = new HashMap<String, Object>();
    }

    try {
      BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
      for (PropertyDescriptor property : propertyDescriptors) {
        String key = property.getName();

        // 过滤class属性
        if (!key.equals("class")) {
          // 得到property对应的getter方法
          Method getter = property.getReadMethod();
          Object value = getter.invoke(obj);
          if (value != null && value.toString().length() > 0) {
            params.put(key, value);
          }
        }

      }
    } catch (Exception e) {
      System.out.println("transBean2Map Error " + e);
    }

    return params;
  }
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (value != null && value.toString().length()>0) {
						map.put(key, value);
					}
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

}