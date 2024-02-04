package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ChiTietLoSanPham extends BaseEntity {
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_lo_SP")
    private LoSanPham loSanPham;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_sp")
    private SanPham sanPham;
}
