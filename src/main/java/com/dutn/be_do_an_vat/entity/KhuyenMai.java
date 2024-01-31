package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
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
}
