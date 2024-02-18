package com.dutn.be_do_an_vat.dto;

import com.dutn.be_do_an_vat.entity.SanPham;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTODanhMuc {
    private Long iddm;
    private String nameDanhMuc;
    private Set<String> sanPhams;
}
