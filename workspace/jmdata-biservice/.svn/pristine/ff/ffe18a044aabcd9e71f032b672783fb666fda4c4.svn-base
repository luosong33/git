package org.jumao.bi.utis;

import java.util.Calendar;
import java.util.Date;

public class RegCalUtils {

	
	
	public static Calendar getCalendarBy(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	
	public static Calendar getCalendarBy(String dateTime) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(RegDateUtils.parseStartWithFF(dateTime));
		return calendar;
	}
	
	
	public static Calendar getCalendarBy(long ms) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(ms);
		return calendar;
	}
	
	
}
