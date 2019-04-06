package astro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final String DATE_TIME_FORMAT = "HH:mm:ss dd-MM-yyyy";
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);

    public static String getCurrentDate(){
        return sDateFormat.format(new Date());
    }
}
