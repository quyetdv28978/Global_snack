package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Loai_San_Pham_Tang;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
@ToString
@Builder
public class DieuKienApDungKhuyenMai extends BaseEntity {
    private int soLuongTang;
    private int dieuKienTang;
    private E_Loai_San_Pham_Tang loaiSanPhamTang;
    private Long sanPhamLaSao;
    @OneToOne
    @JoinColumn(name = "id_km_dieu_kien")
    private KhuyenMai khuyenMai;
}
