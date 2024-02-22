package com.dutn.be_do_an_vat.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTOSanPhamDIscount {
    private Long id;
    private Double giaBan;
    private String moTa, tenSanPham, tieuDe;
    private int soLuongTon;
}
