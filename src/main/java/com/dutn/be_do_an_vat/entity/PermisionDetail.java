package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Bảng phụ giữa role và permision
1 role -><- n permision
 */
@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class PermisionDetail extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "id_permision")
    private Permision permision;
    private String url;
}
