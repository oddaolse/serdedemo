package com.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@io.micronaut.serde.annotation.Serdeable
@Introspected
public class Message {

    private String name;

    @JsonProperty("name")
    @JsonInclude()
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    @JsonInclude()
    public void setName(String activityType) {
        this.name = activityType;
    }

    private List<Content> contents = null;

    @JsonProperty("contents")
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public List<Content> getContents() {
        return contents;
    }

    @JsonProperty("contents")
    @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
    public void setLogContents(List<Content> contents) {
        this.contents = contents;
    }
}
