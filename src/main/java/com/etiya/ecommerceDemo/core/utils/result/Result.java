package com.etiya.ecommerceDemo.core.utils.result;

import lombok.Getter;

@Getter
public class Result {

    private final boolean success;
    private String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result(boolean success) {
        this.success = success;
    }
}
