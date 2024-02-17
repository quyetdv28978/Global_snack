package com.dutn.be_do_an_vat.service;

import com.dutn.be_do_an_vat.dto.AuthenDTO;
import com.dutn.be_do_an_vat.entity.TaiKhoan;
import com.dutn.be_do_an_vat.repositoty.ITaiKhoan;
import com.dutn.be_do_an_vat.repositoty.iRole;
import com.dutn.be_do_an_vat.sercurity.CustomerUserDetails;
import com.dutn.be_do_an_vat.sercurity.JwtService;
import com.dutn.be_do_an_vat.utility.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenService {
    @Autowired
    private ITaiKhoan taiKhoanRes;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private SerTaiKhoan serTaiKhoan;
    @Autowired
    private iRole roleRes;

    public String login(AuthenDTO authenDTO) {
        TaiKhoan taiKhoan = taiKhoanRes.findByTaiKhoan(authenDTO.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException(Const.USER_NOT_FOUND));

        if (!passwordEncoder.matches(authenDTO.getPass(), taiKhoan.getMatKhau()))
            throw new UsernameNotFoundException(Const.USER_NOT_FOUND);

        CustomerUserDetails customerUserDetail = new CustomerUserDetails(taiKhoan);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenDTO.getUserName(), authenDTO.getPass(), customerUserDetail.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        return jwtService.generateToken(authenDTO.getUserName());
    }
}
