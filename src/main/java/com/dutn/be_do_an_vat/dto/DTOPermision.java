package com.dutn.be_do_an_vat.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTOPermision {
    private String permision, url, method;
}
