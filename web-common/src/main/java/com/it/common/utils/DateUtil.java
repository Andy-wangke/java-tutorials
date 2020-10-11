package com.it.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO moving to web common project
public class DateUtil {

    private static Logger s_logger = LoggerFactory.getLogger(DateUtil.class);;
    public static final TimeZone utcTZ = TimeZone.getTimeZone("GMT");

    /*
     * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            s_logger.error(ex.getMessage());
        }
        return xmlCalendar;
    }

    public static Date convertUTCToGMTTime(Date utcDate) {
        if (utcDate == null)
            return null;
        TimeZone mstTimeZone = TimeZone.getTimeZone("MST");

        Calendar mstCal = Calendar.getInstance(mstTimeZone);
        long offset = mstCal.get(Calendar.ZONE_OFFSET) + mstCal.get(Calendar.DST_OFFSET);
        return new Date(utcDate.getTime() + offset * -1);
    }

    public static Date convertGMTToUTCTime(Date gmtDate) {
        if (gmtDate == null)
            return null;
        TimeZone mstTimeZone = TimeZone.getTimeZone("MST");

        Calendar mstCal = Calendar.getInstance(mstTimeZone);
        long offset = mstCal.get(Calendar.ZONE_OFFSET) + mstCal.get(Calendar.DST_OFFSET);
        return new Date(gmtDate.getTime() - offset * -1);
    }

    public static Calendar convertUTCToGMTTime(Calendar utcCal) {
        if (utcCal == null)
            return null;
        TimeZone mstTimeZone = TimeZone.getTimeZone("MST");

        Calendar mstCal = Calendar.getInstance(mstTimeZone);
        long offset = mstCal.get(Calendar.ZONE_OFFSET) + mstCal.get(Calendar.DST_OFFSET);
        mstCal.setTimeInMillis(utcCal.getTimeInMillis() + offset * -1);
        return mstCal;
    }

    public static String dateFormat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(Calendar cal) {
        if (cal == null)
            return null;
        XMLGregorianCalendar xmlCal = null;
        try {
            xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
        } catch (DatatypeConfigurationException e) {
            return null;
        }
        xmlCal.setDay(cal.get(Calendar.DAY_OF_MONTH));
        xmlCal.setMonth(cal.get(Calendar.MONTH) + 1);
        xmlCal.setYear(cal.get(Calendar.YEAR));
        xmlCal.setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        return xmlCal;
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar2(Calendar date) {
        XMLGregorianCalendar xmlDate = null;
        if (date != null) {
            GregorianCalendar gcal = new GregorianCalendar();
            gcal.setTime(date.getTime());

            try {
                xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
            } catch (DatatypeConfigurationException e) {

            }
        }
        return xmlDate;
    }

    public static Calendar getCurrentTimeInGMT() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date today = Calendar.getInstance().getTime();
        String currentDate = df.format(today);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * @param c
     * @return
     */
    public static Calendar convertToMST(Calendar c) {
        Calendar calendar = (Calendar) c.clone();
        TimeZone fromTimezone = calendar.getTimeZone();
        TimeZone toTimezone = TimeZone.getTimeZone("MST");

        calendar.setTimeZone(fromTimezone);
        calendar.add(Calendar.MILLISECOND, fromTimezone.getRawOffset() * -1);
        if (fromTimezone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
        }

        calendar.add(Calendar.MILLISECOND, toTimezone.getRawOffset());
        if (toTimezone.inDaylightTime(calendar.getTime())) {
            calendar.add(Calendar.MILLISECOND, toTimezone.getDSTSavings());
        }

        return calendar;
    }

    public static Calendar convertTimeStampToCalendar(Timestamp timeStamp) {
        if (timeStamp == null) {
            return null;
        }
        Date theDate = new Date();
        theDate.setTime(timeStamp.getTime());
        Calendar theDateCal = new GregorianCalendar();
        theDateCal.setTime(theDate);
        return theDateCal;

    }

    public static Calendar convertDateToCalendar(Date date) {
        if (date == null) {
            return null;
        }
        // Calendar theCal = new GregorianCalendar();
        Calendar theCal = Calendar.getInstance();
        theCal.setTime(date);
        return theCal;
    }

    public static Calendar convertTimestampToCalendar(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        Date theDate = new Date();
        theDate.setTime(timestamp.getTime());
        Calendar theDateCal = new GregorianCalendar();
        theDateCal.setTime(theDate);
        return theDateCal;
    }

    public static void main(String[] args) {
        long currentOffset = 0L;
        Integer limit = 20;
        System.out.println(44 / 20);
        System.out.println(19 % 20);
        for (int i = 0; i < 10; i++) {
            currentOffset += limit;
            System.out.println(currentOffset);
            System.out.println(limit);
        }
    }
}
