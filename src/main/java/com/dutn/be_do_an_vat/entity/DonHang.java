package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class DonHang extends BaseEntity {
    private Double tongTien;
    private int trangThai;
    @OneToMany(mappedBy = "donHang")
    private Set<DonHangChiTiet> donHangChiTiets;
}
