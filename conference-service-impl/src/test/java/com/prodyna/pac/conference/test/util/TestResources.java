package com.prodyna.pac.conference.test.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.enterprise.inject.Produces;


public class TestResources {


	@Produces
	public Date init(){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		
		return calendar.getTime();
	}
}
