package com.dutn.be_do_an_vat.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OutDateDTO {
    private Long ngayHetHan;
    private String tenSanPham;
}
