package com.example;

import io.micronaut.core.type.Argument;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class DemoTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    ObjectMapper objectMapper;


    final String someContent = """
            {
                "someKey": "someValue"
            }
            """;

    final String someMessage = """

              {
                "name": "Joe",

                "logContents": [
                  {
                    "someKey": "someValue",
                    "anotherKey": "anotherValue"
                  }
                ]
              }

            """;
    @Test
    /*
      This test succeeds
     */
    void serdeHashMap() throws IOException {

        List contentList = Arrays.asList(objectMapper.readValue(someContent, Argument.mapOf(String.class, String.class)));

        HashMap<String, String> content = (HashMap<String, String>) contentList.get(0);
        var someValue = content.get("someKey");
        assertEquals("someValue", someValue);

    }

    @Test
    /*
       This test fails - content list contains one empty element
     */
    void serdeContent() throws IOException {

        List<Content> contentList = Arrays.asList(objectMapper.readValue(someContent, Argument.of(Content.class)));

        Content content = contentList.get(0);
        var someValue = content.get("someKey");
        assertEquals("someValue", someValue);

    }


    @Test
    /* this test fails - the name is parsed, but empty for Content*/
    void serdeMessage() throws IOException {

        List<Message> messageList = Arrays.asList(objectMapper.readValue(someMessage, Argument.of(Message.class)));


        Message message = messageList.get(0);
        String name = message.getName();

        // this assert will pass
        assertEquals("Joe", name);

        List<Content> contentList = message.getContents();
        assertNotNull(contentList);

        Content content = contentList.get(0);
        var someValue = content.get("someKey");

        //this assert will not pass
        assertEquals("someValue", someValue);

    }
}
