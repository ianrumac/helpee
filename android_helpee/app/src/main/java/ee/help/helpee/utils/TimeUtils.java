package ee.help.helpee.utils;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by infinum on 02/05/15.
 */
public class TimeUtils {

    public static String parseDateTimeIntoShowableString(String eventDate) {

        String[] dateParts = eventDate.split("T");

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
        DateTimeFormatter hourTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

        DateTime dateTimeOfEvent = dateTimeFormatter.parseDateTime(dateParts[0]);
        DateTime hourTimeOfEvent = hourTimeFormatter.parseDateTime(dateParts[1]);
        DateTimeFormatter parseDateToShow = DateTimeFormat.forPattern("dd.MM.YYYY");
        dateTimeOfEvent = dateTimeOfEvent.withHourOfDay(hourTimeOfEvent.getHourOfDay()).withMinuteOfHour(hourTimeOfEvent.getMinuteOfHour());


        StringBuilder eventDateBuilder = new StringBuilder();
        if (dateTimeOfEvent.getDayOfYear() == (DateTime.now().getDayOfYear())) {
            eventDateBuilder.append("Today at ");
        } else if ((dateTimeOfEvent.getDayOfYear() - 1) == (DateTime.now().getDayOfYear())) {
            eventDateBuilder.append("Tomorrow at ");
        } else {
            eventDateBuilder.append(parseDateToShow.print(dateTimeOfEvent)).append(" at ");
        }

        if(hourTimeOfEvent.getHourOfDay()<10)
            eventDateBuilder.append("0");
        eventDateBuilder.append(hourTimeOfEvent.getHourOfDay()).append(":");

        if(hourTimeOfEvent.getMinuteOfHour()<10)
            eventDateBuilder.append("0");

        eventDateBuilder.append(dateTimeOfEvent.getMinuteOfHour());

        return eventDateBuilder.toString();

    }

    public static boolean hasEventPassed(String eventDate) {

        String[] dateParts = eventDate.split("T");

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
        DateTimeFormatter hourTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

        DateTime dateTimeOfEvent = dateTimeFormatter.parseDateTime(dateParts[0]);
        DateTime hourTimeOfEvent = hourTimeFormatter.parseDateTime(dateParts[1]);

        dateTimeOfEvent = dateTimeOfEvent.withHourOfDay(hourTimeOfEvent.getHourOfDay()).withMinuteOfHour(hourTimeOfEvent.getMinuteOfHour());
        if(dateTimeOfEvent.getMillis()<DateTime.now().getMillis())
            return true;
        else
        return false;

    }

}
