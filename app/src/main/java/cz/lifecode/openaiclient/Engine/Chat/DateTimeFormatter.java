package cz.lifecode.openaiclient.Engine.Chat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimeFormatter {
    protected SimpleDateFormat formatter;
    protected Calendar calendar;

    public DateTimeFormatter() {
        calendar = Calendar.getInstance();
    }

    protected void resetCalendarTime() {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public String formatForChatMessage(Date date) {
        resetCalendarTime();

        if (date.compareTo(calendar.getTime()) < 0) {
            formatter = new SimpleDateFormat("dd. E HH:mm", Locale.getDefault());
        } else {
            formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
        }

        return formatter.format(date);
    }
}
