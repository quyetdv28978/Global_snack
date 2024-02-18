package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DTOKhachHang {
    private Long idkh;
    private String tenKhachHang;
    private Set<String> diaChis;
}
