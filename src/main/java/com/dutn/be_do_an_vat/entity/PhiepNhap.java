package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "phieu_nhap")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class PhiepNhap extends BaseEntity {
    private String maPhieuNhap;
    private String tenPhieuNhap;
    private LocalDate ngayNhap;
    private int soLuong;
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private Double giaBan;

    @ManyToOne
    @JoinColumn(name = "id_ncc")
    private NhaCungCap nhaCungCap;
}
