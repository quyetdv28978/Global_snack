package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DonHang extends BaseEntity {
    private Double tongTien;
    private LocalDate ngayThanhToan;
    private LocalDate ngayTao;
    private LocalDate thoiGianDuKien;
    private Double phiShip;
    private int trangThai;
    @OneToMany(mappedBy = "donHang")
    private Set<DonHangChiTiet> donHangChiTiets;

    @OneToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private KhachHang khachHang;
}
