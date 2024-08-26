package com.restapi.usermanagement.adapter.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private Object message;
    private LocalDateTime timestamp;
    private Integer status;
}
