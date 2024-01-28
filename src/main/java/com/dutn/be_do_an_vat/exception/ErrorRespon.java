package com.dutn.be_do_an_vat.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorRespon {
    private int statusCode;
    private Date timestamp;
    private List<String> message;
    private String description;
}
