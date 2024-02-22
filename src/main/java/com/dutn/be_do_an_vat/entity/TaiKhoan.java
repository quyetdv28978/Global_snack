package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Loai_TK;
import com.dutn.be_do_an_vat.utility.swagerConstan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tai_khoan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaiKhoan extends BaseEntity {
    @Schema(example = swagerConstan.TK_USER)
    private String taiKhoan;
    @Schema(example = swagerConstan.TK_PASS)
    private String matKhau;
    @Enumerated(EnumType.STRING)
    private E_Loai_TK loaiTK;

    @OneToMany(mappedBy = "taiKhoan")
    private Set<Role_Detail> roleDetails;

    @OneToOne(mappedBy = "taiKhoan")
    @JsonIgnore
    private NhanVien nhanVien;

    @OneToOne(mappedBy = "taiKhoan")
    @JsonIgnore
    private KhachHang khachHang;


}
