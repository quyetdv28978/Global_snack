package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "dia_chi")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Builder
public class DiaChi extends BaseEntity {
    private String huyen;
    private String xa;
    private String tinh;
    private String soNha;
    private String diaChi;
    private int trangThai;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "id_kh")
    private KhachHang khachHang;
}
