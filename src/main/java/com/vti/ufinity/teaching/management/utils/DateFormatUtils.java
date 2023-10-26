package com.vti.ufinity.teaching.management.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
@Slf4j
public final class DateFormatUtils {

    private DateFormatUtils() {
    }

    public static final String DATE_MMDDYYYY_LONG = "MM-dd-yyyy";

    public static final String DATE_FORMAT_DEFAULT = DATE_MMDDYYYY_LONG;


    public static String format(Date date) {

        return format(date, DATE_FORMAT_DEFAULT);
    }

    public static String format(Date date, String pattern) {

        if (date == null) {
            return StringUtils.EMPTY;
        }

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

            return dateFormatter.format(date);
        } catch (Exception e) {

            log.warn(pattern + " is invalid pattern date.");

            return StringUtils.EMPTY;
        }
    }

    public static Date parse(String value) {

        return parse(value, DATE_FORMAT_DEFAULT);
    }

    public static Date parse(String value, String pattern) {

        if (StringUtils.isBlank(value)) {
            return null;
        }

        try {

            SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);

            return dateFormatter.parse(value);
        } catch (ParseException e) {
            log.warn("value: " + value + " or pattern: " + pattern + " is invalid.");

            return null;
        }
    }
}
