package com.dutn.be_do_an_vat.entity;

import com.dutn.be_do_an_vat.entity.base_entity.BaseEntity;
import com.dutn.be_do_an_vat.entity.base_entity.E_Loai_TK;
import com.dutn.be_do_an_vat.utility.swagerConstan;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaiKhoan extends BaseEntity {
    @Schema(example = swagerConstan.TK_USER)
    private String taiKhoan;
    @Schema(example = swagerConstan.TK_PASS)
    private String matKhau;
    @Enumerated(EnumType.STRING)
    private E_Loai_TK loaiTK;
    @OneToOne
    @JoinColumn(name = "id_role")
    private Role role;
}
