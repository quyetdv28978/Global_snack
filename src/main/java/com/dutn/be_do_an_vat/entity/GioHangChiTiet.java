package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "gio_hang_chi_tiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GioHangChiTiet extends BaseEntity {
    private Double giaTien;
    private int soLuong;

    @ManyToOne
    @JoinColumn(name = "id_sp")
    private SanPham sanPhams;

    @ManyToOne
    @JoinColumn(name = "id_gio_hang")
    @JsonIgnore
    @ToString.Exclude
    private GioHang gioHangs;
}
