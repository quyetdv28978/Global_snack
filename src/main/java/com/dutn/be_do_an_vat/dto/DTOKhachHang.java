package com.dutn.be_do_an_vat.dto;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Gioi_Tinh;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate DOB;
    private int age;
    @Enumerated(EnumType.STRING)
    private E_Gioi_Tinh gioiTinh;
    private int trangThai;
    private Set<String> diaChis;
}
