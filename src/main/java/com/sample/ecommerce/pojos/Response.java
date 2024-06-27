package com.sample.ecommerce.pojos;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Response<T> {
    private T data;
    private String message;
    private Status status;

    public enum Status {
        SUCCESS, FAILURE, ERROR
    }
}
