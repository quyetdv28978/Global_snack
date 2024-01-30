package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class NhanVien extends BaseUser {
    @OneToOne
    @JoinColumn(name = "id_tk")
    private TaiKhoan taiKhoan;

    @OneToMany
    @JoinColumn(name = "id_don_hang")
    private Set<DonHang> donHangs;

    @OneToMany
    @JoinColumn(name = "id_phieu_nhap")
    private Set<PhiepNhap> phiepNhaps;
}
