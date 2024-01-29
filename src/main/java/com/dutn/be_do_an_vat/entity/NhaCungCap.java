package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class NhaCungCap extends BaseEntity {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChiNhaCung;
    private String sdt;
    private String email;

}
