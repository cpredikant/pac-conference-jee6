package de.predikant.conference.test.util;

import javax.enterprise.inject.Produces;
import java.text.SimpleDateFormat;


public class TestResources {

    @Produces
    public SimpleDateFormat dateformatter() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    }
}
