package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

/*
Bảng phụ giữa role và permision
1 role -><- n permision
 */
@Entity
@Table(name = "permision_detail")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PermisionDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_role")
    @JsonIgnore
    @ToString.Exclude
    private Role role;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_permision")
    private Permision permision;
}
