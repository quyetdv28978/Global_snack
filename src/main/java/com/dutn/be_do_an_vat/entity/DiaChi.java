package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class DiaChi extends BaseEntity {
    private String huyen;
    private String xa;
    private String tinh;
    private String soNha;
    private int trangThai;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    @JoinColumn(name = "id_kh")
    private KhachHang khachHang;
}
