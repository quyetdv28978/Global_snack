package com.dutn.be_do_an_vat.sercurity;

import com.dutn.be_do_an_vat.entity.TaiKhoan;
import com.dutn.be_do_an_vat.repositoty.ITaiKhoan;
import com.dutn.be_do_an_vat.repositoty.iRole;
import com.dutn.be_do_an_vat.service.MessageService;
import com.dutn.be_do_an_vat.utility.Const;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ITaiKhoan iUserReporitory;

    @Autowired
    private MessageService messageService;
    /**
     * Ghi đè lại phương thức findByUsername của UserDetailsService.
     * Gọi đến userReporitory trả lại User theo username
     * Tạo CustomUserDetails từ User tìm được
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan user = iUserReporitory.findByTaiKhoan(username).orElseThrow(()
                -> new NotFoundException(messageService.getMessage(Const.USER_NOT_FOUND) + username));
        return new CustomerUserDetails(user);
    }
}
