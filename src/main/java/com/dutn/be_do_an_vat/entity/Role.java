package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/*
Ánh xạ với bảng role trong db -> chức vụ của user
role giá trị mặc định : ADMIN, USER
trang thai : 0 -> hoạt động, 1 -> dừng
 */
@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Role extends BaseEntity {
    private String role;
    private int trangThai;
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<PermisionDetail> permisions;


}
