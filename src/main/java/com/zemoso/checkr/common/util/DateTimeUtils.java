package com.zemoso.checkr.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeUtils {

    private DateTimeUtils(){}

    public static LocalDateTime getDtCurrentInUtc(){
        return ZonedDateTime.now(ZoneOffset.UTC).toLocalDateTime();
    }
}
