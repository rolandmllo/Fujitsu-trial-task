package app.services.helper;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

public class TimestampXMLAdapter extends XmlAdapter<String, LocalDateTime> {
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
        public String marshal(LocalDateTime dateTime) {
            return dateTime.format(dateFormat);
        }

        @Override
        public LocalDateTime unmarshal(String dateTime) {
            return LocalDateTime
                    .ofInstant(Instant.ofEpochSecond(
                            Long.parseLong(dateTime)),
                            TimeZone.getDefault().toZoneId());

        }

    }