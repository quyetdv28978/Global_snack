package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Gioi_Tinh;
import com.dutn.be_do_an_vat.entity.base_entity.BaseUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "khach_hang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class KhachHang extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String fullName;
    private LocalDate DOB;
    private int age;
    @Enumerated(EnumType.STRING)
    private E_Gioi_Tinh gioiTinh;
    private int trangThai;
    @CreatedBy
    private String createBy;
    @CreatedDate
    private LocalDate createDate;
    @LastModifiedBy
    private String lastUpdatedBy;
    @LastModifiedDate
    private LocalDate lastUpdatedDate;

    @OneToOne
    @JoinColumn(name = "id_tk2")
    private TaiKhoan taiKhoan;

    @OneToOne(mappedBy = "khachHang", cascade = CascadeType.REMOVE)
    private GioHang gioHang;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.REMOVE)
    private Set<DiaChi> diaChis;

    @OneToMany(mappedBy = "khachHang")
    private Set<HoaDon> hoaDons;

}
