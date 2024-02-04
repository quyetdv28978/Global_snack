package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DTOSanPham {
    private String tenSanPham;
    private Double giaBan;
    private int soLuongTon;
    private String mota;
    private String tieuDe;
    private Set<String> images;
    private Integer idDanhMuc;
    private int trangThai;

    private String maLo;
    private String tenLo;
    private LocalDate ngayHetHan;
    private LocalDate ngaySanXuat;
    private LocalDate ngayNhap;
    private Double giaNhap;
}
