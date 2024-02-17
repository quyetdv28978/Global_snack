package com.dutn.be_do_an_vat.dto;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Loai_TK;
import lombok.*;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTOTaiKhoan {
    private String taiKhoan;
    private E_Loai_TK loaiTK;
    private String role;
    private Map<String, String> permisions;
}
