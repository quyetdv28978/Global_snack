package com.dutn.be_do_an_vat.sercurity;

import com.dutn.be_do_an_vat.entity.Permision;
import com.dutn.be_do_an_vat.entity.Role;
import com.dutn.be_do_an_vat.entity.TaiKhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Customer User khi implement UserDetails
 */
public class CustomerUserDetails implements UserDetails {

    private TaiKhoan user;

    public CustomerUserDetails(TaiKhoan user) {
        this.user = user;
    }

    //Ghi đè lại phương thức getAuthorities của UserDetails.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List a = user.getRoleDetails().stream().map(i -> new SimpleGrantedAuthority(i.getRole().getRole()))
                .collect(Collectors.toList());
        a.forEach(System.out::println);
        return a;

    }

    //Ghi đè lại phương thức getPassword của UserDetails.
    @Override
    public String getPassword() {
        return this.user.getMatKhau();
    }

    //Ghi đè lại phương thức getUsername của UserDetails.
    @Override
    public String getUsername() {
        return this.user.getTaiKhoan();
    }

    //Ghi đè lại phương thức isAccountNonExpired của UserDetails.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //Ghi đè lại phương thức isAccountNonLocked của UserDetails.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Ghi đè lại phương thức isCredentialsNonExpired của UserDetails.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Ghi đè lại phương thức isEnabled của UserDetails.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
