package de.predikant.conference.test.util;

import java.text.SimpleDateFormat;

import javax.enterprise.inject.Produces;


public class TestResources {
	
	@Produces
	public SimpleDateFormat dateformatter(){
		return  new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	}
}
