package com.dutn.be_do_an_vat.dto;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Loai_San_Pham_Tang;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DTODieuKienKhuyenMai extends DTOKhuyenMai{
    private int soLuongTang;
    private int dieuKienTang;
    private E_Loai_San_Pham_Tang loaiSanPhamTang;
    private Long sanPhamLaSao;
}
