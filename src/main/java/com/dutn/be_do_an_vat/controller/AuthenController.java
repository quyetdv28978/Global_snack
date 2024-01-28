package com.dutn.be_do_an_vat.controller;

import com.dutn.be_do_an_vat.dto.AuthenDTO;
import com.dutn.be_do_an_vat.dto.DTOSanPham;
import com.dutn.be_do_an_vat.service.AuthenService;
import com.dutn.be_do_an_vat.service.SerSanPham;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${project.endpont.v1}")
@Tag(name = "Login", description = "login APIs")
public class AuthenController {
    @Autowired
    private AuthenService authenService;

    @Operation(summary = "API login", description = "trả về trang thai login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Thành công"),
            @ApiResponse(responseCode = "403", description = "Không có quyền truy cập"),
    })
    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenDTO authenDTO) {
        return ResponseEntity.ok().body(authenService.login(authenDTO));
    }
}
