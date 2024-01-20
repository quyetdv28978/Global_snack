package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SanPham extends BaseEntity {
    private String tenSanPham;
    private Double giaBan;
    private Integer soLuongTon;
    private int trangThai;
}
