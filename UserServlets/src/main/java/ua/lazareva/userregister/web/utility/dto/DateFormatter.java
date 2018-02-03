package ua.lazareva.userregister.web.utility.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.text.DateFormat.MEDIUM;

public class DateFormatter {
    public static final String DATE_FORMAT_INTO_UI = getDateLocalSettings();
    public static final String DATE_FORMAT_FROM_UI = "yyyy-MM-dd";

    private static String getDateLocalSettings() {
        Locale locale = Locale.getDefault();
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(MEDIUM, locale);
        return simpleDateFormat.toPattern();
    }
}
