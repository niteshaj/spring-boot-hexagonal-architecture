package com.example.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by njawanj on 27/06/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorResponse {
    private String code;

    private String message;

    private List<Object> properties;

    private String detailMessage;
}
