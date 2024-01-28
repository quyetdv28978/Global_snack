package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTOUpdateROle {
    private Long idRole;
    private String role;
    private List<Integer> idRoles;
    private int trangThai;
}
