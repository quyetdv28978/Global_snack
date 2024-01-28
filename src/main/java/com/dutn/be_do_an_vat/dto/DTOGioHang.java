package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DTOGioHang {
    private long idGioHang;
    private Long idsp;
    private Double giaTien;
    private Integer soLuong;
}
