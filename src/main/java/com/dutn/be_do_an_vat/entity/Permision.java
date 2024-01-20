package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.E_Permision_Method;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
/*
Permision ánh xạ bảng permision trong db -> phần quyền role của user được làm gì trong ứng dụng
method -> POST, PUT, DELETE, GET
url -> endpoint api
 */
public class Permision extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private E_Permision_Method method;
}
