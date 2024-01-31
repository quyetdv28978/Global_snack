package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.E_Permision_Method;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permision")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
/*
Permision ánh xạ bảng permision trong db -> phần quyền role của user được làm gì trong ứng dụng
method -> POST, PUT, DELETE, GET
url -> endpoint api
 */
public class Permision extends BaseEntity {
    private String namePermision;
    @Enumerated(EnumType.STRING)
    private E_Permision_Method method;
    private String url;
}
