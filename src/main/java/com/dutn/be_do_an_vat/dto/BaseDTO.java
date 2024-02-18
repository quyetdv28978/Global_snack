package com.dutn.be_do_an_vat.dto;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEnum.E_Gioi_Tinh;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseDTO {
    private Long id;
    private String fullName;
    private LocalDate DOB;
    private E_Gioi_Tinh gioiTinh;
}
