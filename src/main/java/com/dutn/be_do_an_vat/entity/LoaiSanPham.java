package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "loai_san_pham")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class LoaiSanPham extends BaseEntity {
    private String tenLoaiSanPham;
    private int trangThai;

    @OneToOne
    @JoinColumn(name = "id_sp")
    SanPham sanPham;
}
