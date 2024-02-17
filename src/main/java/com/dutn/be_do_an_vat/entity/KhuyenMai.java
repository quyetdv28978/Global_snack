package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "khuyen_mai")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class KhuyenMai extends BaseEntity {
    private String tenKhuyenMai;
    private LocalDate ngayBatDau, ngayKetThuc;
    private int trangThai;
    private Double giaTriKhuyenMai;

    @ManyToOne
    @JoinColumn(name = "id_lkm")
    private LoaiKhuyenMai loaiKhuyenMai;
}
