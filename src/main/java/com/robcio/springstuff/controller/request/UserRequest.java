package com.robcio.springstuff.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String name;
    private String email;
    private Integer age;
}
