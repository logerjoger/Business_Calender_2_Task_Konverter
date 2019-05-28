package de.logerbyte.businesscalendarconverter.ical;

public class IcalConstants {

    // KEYS
    public static final String BEGIN_KEY = "BEGIN:";
    public static final String END_KEY = "END:";
    public static final String EVENT_SUMMARY = "SUMMARY:";
    public static final String EVENT_DESCRIPTION = "DESCRIPTION:";


    // VALUES
    public static final String CAL_VALUE = "VCALENDAR";
    public static final String EVENT_VALUE = "VEVENT";


    public static String createVcalHeader() {
        return "BEGIN:VCALENDAR\n" +
                "VERSION:2.0\n";
    }

    public static String createEnd() {
        return "END:VCALENDAR";
    }

public static String createEvent(String startTime, String endTime, String summary, String description){
    return "BEGIN:VEVENT\n" +
            "SUMMARY:"+summary+"\n" +
            "DESCRIPTION:"+description+"\n" +
            "DTSTART:"+startTime+"\n" +
            "DTEND:"+endTime+"\n" +
            "END:VEVENT\n";
}


}
