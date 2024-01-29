package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class ChiTietKhuyenMai extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_KM")
    private KhuyenMai khuyenMai;
    @ManyToOne
    @JoinColumn(name = "id_SP")
    private SanPham sanPham;
}
