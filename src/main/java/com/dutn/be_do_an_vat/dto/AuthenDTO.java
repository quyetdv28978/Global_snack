package com.dutn.be_do_an_vat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenDTO {
    private String userName;
    private String pass;
}
