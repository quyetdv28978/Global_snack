package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Builder
public class LoSanPham extends BaseEntity {
    private String maLo;
    private String tenLo;
    private LocalDateTime ngayNhap;
    private int soLuongTon;
    private LocalDate ngaySanXuat;
    private LocalDate ngayHetHan;
    private Double giaBan;
    private Double giaNhap;
    private Double tongTien;

    @ManyToOne
    @JoinColumn(name = "id_ncc")
    private NhaCungCap nhaCungCap;

    @OneToMany(mappedBy = "loSanPham")
    private Set<ChiTietLoSanPham> chiTietLoSanPhams;
}
