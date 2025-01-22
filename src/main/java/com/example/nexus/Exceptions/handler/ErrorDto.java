package com.example.nexus.Exceptions.handler;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDto {

    private Integer httpCode;
    private ErrorCodes code;
    private String message;
    private List<String> errors;
}
