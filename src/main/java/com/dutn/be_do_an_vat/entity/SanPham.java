package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "san_pham")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SanPham extends BaseEntity {
    private String tenSanPham;
    private Double giaBan;
    private Integer soLuongTon;
    private String mota;
    private String tieuDe;
    private int trangThai;

    @OneToMany(mappedBy = "sanPham")
    @ToString.Exclude
    private Set<Images> images;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    private Set<DanhMucChiTiet> danhMucChiTiets;
}
