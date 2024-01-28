package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.DTORole;
import com.dutn.be_do_an_vat.dto.DTOUpdateROle;
import com.dutn.be_do_an_vat.dto.DTOUpdateRoleUser;
import com.dutn.be_do_an_vat.service.SerRole;
import com.dutn.be_do_an_vat.utility.Const;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Chuc vu", description = "Chuc vu APIs")
@RequestMapping("${project.endpont.v1}/role")
public class RoleController {
    @Autowired
    private SerRole serRole;

    @Operation(summary = "API them chuc vu", description = "trả về Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping()
    public ResponseEntity themRole(@RequestBody DTORole dtoRole) {
        return ResponseEntity.ok().body(serRole.addRole(dtoRole));
    }

    @Operation(summary = "API sua chuc vu cua user", description = "trả về user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
            @ApiResponse(responseCode = "400", description = "Không tìm thấy user")
    })
    @PostMapping("/{idUser}")
    public ResponseEntity updateRoleUser(@PathVariable Long idUser, @RequestBody DTOUpdateRoleUser dtoUpdateRoleUser) {
        return ResponseEntity.ok().body(serRole.updateRoleUser(idUser, dtoUpdateRoleUser.getIdRoles()));
    }

    @Operation(summary = "API sua chuc vu", description = "trả về chuc vu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PutMapping
    public ResponseEntity updateRole(@RequestBody DTOUpdateROle dtoRole) {
        return ResponseEntity.ok().body(serRole.updateRole(dtoRole));
    }

    @Operation(summary = "API xoa chuc vu", description = "trả về susscess")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @DeleteMapping("/{idRole}")
    public ResponseEntity deleteRole(@PathVariable Long idRole) {
        serRole.deleteRole(idRole);
        return ResponseEntity.ok().body(Const.DELETE_SUSSCESS);
    }
}
