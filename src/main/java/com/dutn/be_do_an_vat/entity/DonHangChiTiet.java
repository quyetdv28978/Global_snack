package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "don_hang_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DonHangChiTiet extends BaseEntity {
    @ManyToOne()
    @JoinColumn(name = "id_sanpham")
    SanPham sanPham;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_donhang")
    @JsonIgnore
    DonHang donHang;
    private int soLuong;
    private Double tongTien;
}
