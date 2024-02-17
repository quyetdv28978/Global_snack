package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class DTOKhuyenMai {
    private String tenKhuyenMai;
    private LocalDate ngayBatDau, ngayKetThuc;
    private int trangThai;
    private Double giaTriKhuyenMai;
    private String loaiKhuyenMai;
    private Set<Long> idsps;
}
