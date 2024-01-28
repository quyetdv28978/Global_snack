package com.dutn.be_do_an_vat.entity.base_entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MappedSuperclass
public class BaseUser extends BaseEntity{
    private String name;
    private String fullName;
    private LocalDate DOB;
    private int age;
    @Enumerated(EnumType.STRING)
    private E_Gioi_Tinh gioiTinh;
    private int trangThai;
}
