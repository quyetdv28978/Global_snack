package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTORole {
    private Long idRole;
    private String role;
    private Set<DTOPermision> dtoPermision;
    private int trangThai;
}
