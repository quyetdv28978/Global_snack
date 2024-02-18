package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "danh_muc")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class DanhMuc extends BaseEntity {
    private String nameDanhMuc;

    @OneToMany(mappedBy = "danhMuc", cascade = CascadeType.ALL)
    private Set<DanhMucChiTiet> danhMucChiTiet;
}
