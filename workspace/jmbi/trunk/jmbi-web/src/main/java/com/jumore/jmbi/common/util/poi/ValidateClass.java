/**
 * 
 */
package com.jumore.jmbi.common.util.poi;

import java.lang.annotation.*;

/**
 * 用在ExcelExtractor的值对像的方法上，告诉ExcelExtractor，当调用设置方法时，哪些验证方法应该被调用
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidateClass {
	/**
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	Class value();
}
