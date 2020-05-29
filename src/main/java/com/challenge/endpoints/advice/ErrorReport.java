package com.challenge.endpoints.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ErrorReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;
    private  String message;
    private LocalDateTime data;

}
