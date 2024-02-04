package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nha_cung_cap")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Builder
public class NhaCungCap extends BaseEntity {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChiNhaCung;
    private String sdt;
    private String email;
}
