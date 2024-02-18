package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "danh_muc_chi_tiet")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class DanhMucChiTiet extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_danh_muc")
    @JsonIgnore
    private DanhMuc danhMuc;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;
}
