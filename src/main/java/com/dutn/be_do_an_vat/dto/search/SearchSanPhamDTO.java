package com.dutn.be_do_an_vat.dto.search;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SearchSanPhamDTO {
    private String name;
    private Long idsp;
}
