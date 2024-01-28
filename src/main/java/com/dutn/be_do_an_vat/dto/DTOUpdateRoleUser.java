package com.dutn.be_do_an_vat.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DTOUpdateRoleUser {
    private List<Integer> idRoles;
}
