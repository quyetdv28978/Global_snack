package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Gioi_Tinh;
import com.dutn.be_do_an_vat.entity.base_entity.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "khach_hang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
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
    private String name;
    private String fullName;
    private LocalDate DOB;
    private int age;
    @Enumerated(EnumType.STRING)
    private E_Gioi_Tinh gioiTinh;
    private int trangThai;
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.REMOVE)
    private Set<DiaChi> diaChis;

}
