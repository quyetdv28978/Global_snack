package com.dutn.be_do_an_vat.entity.base_entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseUser extends BaseEntity{
    private String name;
    private String fullName;
    private LocalDate DOB;
    private int age;
    private E_Gioi_Tinh gioiTinh;
    private int trangThai;
}
