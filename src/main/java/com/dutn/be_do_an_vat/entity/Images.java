package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Images extends BaseEntity {
    private String image;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_sanpham")
    private SanPham sanPham;
}
