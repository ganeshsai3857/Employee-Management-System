package com.gl.ems.utility;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Error {

    private List<String> message;

    private String statusCode;

    private LocalDateTime timestamp;
}
