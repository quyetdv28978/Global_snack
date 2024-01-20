package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.E_Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/*
Ánh xạ với bảng role trong db -> chức vụ của user
role giá trị mặc định : ADMIN, USER
trang thai : 0 -> hoạt động, 1 -> dừng
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private E_Role role;
    private int trangThai;

    @OneToMany(mappedBy = "role")
    private Set<PermisionDetail> permisions;
}
