package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "nhan_vien")
@NoArgsConstructor
@Getter
@Setter
public class NhanVien extends BaseUser {
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_tk")
    private TaiKhoan taiKhoan;

    @OneToMany
    @JoinColumn(name = "id_don_hang")
    private Set<DonHang> donHangs;

    @OneToMany
    @JoinColumn(name = "id_phieu_nhap")
    private Set<LoSanPham> loSanPhams;
}
