package com.prodyna.pac.conference.common.util;

import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
	
	private DateUtil (){
		
	}

	public static Date addMinutesToDate(Date date, int minutes) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);

		return c.getTime();
	}

}
