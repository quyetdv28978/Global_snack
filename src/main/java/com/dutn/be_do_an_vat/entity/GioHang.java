package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GioHang extends BaseEntity {
    @CreatedDate
    private LocalDate ngayTao;
    private int trangThai;

    @OneToOne
    @JoinColumn(name = "id_kh")
    @JsonIgnore
    private KhachHang khachHang;

    @OneToMany(mappedBy = "gioHangs")
    private List<GioHangChiTiet> gioHangChiTiets;
}
