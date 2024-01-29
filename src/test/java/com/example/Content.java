package com.example;

import io.micronaut.core.annotation.Introspected;

import java.util.HashMap;

@io.micronaut.serde.annotation.Serdeable
@Introspected
public class Content extends HashMap<String, String> {

}
