package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang extends BaseUser {
    @OneToOne(mappedBy = "khachHang", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private GioHang gioHang;
    @OneToOne(mappedBy = "khachHang")
    @ToString.Exclude
    private DonHang donHang;
    @OneToOne
    @JoinColumn(name = "id_tk")
    private TaiKhoan taiKhoan;
}
