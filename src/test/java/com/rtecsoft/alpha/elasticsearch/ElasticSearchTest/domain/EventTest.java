package com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.util.JsonSerializerUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EventTest {
    @Test
    public void dateTest() {
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        String eventString = "{\n" +
                "\t\"_id\": \"\",\n" +
                "\t\"name\": \"test date\",\n" +
                "\t\"startDate\": \"2019-04-07T14:30:00.000-05:00\"\n" +
                "}";

        ObjectMapper mapper = JsonSerializerUtil.newObjectMapper();
        Event event = null;
        try {
            ZonedDateTime expectedDate = ZonedDateTime.parse("2019-04-07T14:30:00.000-05:00");
            System.out.println(expectedDate);
            event = mapper.readValue(eventString, Event.class);
            System.out.println(event.getStartDate());
            Assert.assertTrue(event.getStartDate().toInstant().compareTo(expectedDate.toInstant()) == 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
