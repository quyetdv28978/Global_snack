package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ship")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@Builder
public class ship extends BaseEntity {
    private String maShip;
    private LocalDate ngayShip;
    private String nhaVanChuyen;
    private Double chiPhi;
    private int trangThai;
}
