package com.dutn.be_do_an_vat.exception;

import lombok.*;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MessErro {
    private String msg;
    private int status;
    private Optional data;
}
