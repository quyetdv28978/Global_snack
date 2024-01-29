package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class PhieuGiamGia extends BaseEntity {
    private String tenPhieuGiam;
    private String loaiPhieuGiamGia;
    private LocalDate ngayBatDau, ngayKetThuc;
    private Double giaTriPhieuGiam;
    private Double dieuKienGiam;

    @OneToOne
    @JoinColumn(name = "id_dh")
    private DonHang donHang;
}
