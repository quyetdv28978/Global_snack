package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.E_Hinh_Thuc_Thanh_Toan;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HinhThucThanhToan extends BaseEntity {
    private E_Hinh_Thuc_Thanh_Toan hinhThucThanhToan;
}
