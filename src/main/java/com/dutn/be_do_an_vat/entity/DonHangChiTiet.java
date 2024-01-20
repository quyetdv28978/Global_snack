package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class DonHangChiTiet extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sanpham")
    SanPham sanPham;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_donhang")
    @JsonIgnore
    DonHang donHang;
    private int soLuong;
    private Double tongTien;
}
