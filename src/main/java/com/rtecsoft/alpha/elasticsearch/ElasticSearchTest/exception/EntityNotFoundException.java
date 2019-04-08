package com.rtecsoft.alpha.elasticsearch.ElasticSearchTest.exception;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Type;
import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(@NotNull String id) {
        super("Could not find " + id);
    }

    public < T > EntityNotFoundException(@NotNull T id) {
        this (id.toString());
    }
}
