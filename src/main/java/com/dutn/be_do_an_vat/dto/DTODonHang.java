package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DTODonHang {
    DTOSanPham sanPhams;
    private Long idDonHang;
    private Double tongTien;
    private int soLuong;
    private int trangThai;
}
