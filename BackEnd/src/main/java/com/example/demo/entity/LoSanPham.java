package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Builder
public class LoSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreatedBy
    private String createBy;
    @CreatedDate
    private LocalDate createDate;
    @LastModifiedBy
    private String lastUpdatedBy;
    @LastModifiedDate
    private LocalDate lastUpdatedDate;
    private String maLo;
    private String tenLo;
    private LocalDateTime ngayNhap;
    private LocalDate ngayHetHan;
    private int trangThai;
    private int soLuong;
    private Double giaBan;
    @ManyToOne
    @JoinColumn(name = "id_ct_san_pham")
    private SanPhamChiTiet sanPhamChiTiet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ncc")
    private NhaCungCap nhaCungCap;
}
