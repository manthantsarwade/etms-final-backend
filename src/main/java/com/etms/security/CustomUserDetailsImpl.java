package com.etms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.etms.pojos.Admin;
import com.etms.pojos.Employee;
import com.etms.pojos.Manager;
import com.etms.pojos.Person;

import java.util.Collection;
import java.util.List;

public class CustomUserDetailsImpl implements UserDetails {
    private final Person user;


    public CustomUserDetailsImpl(Person user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "";
         if (user instanceof Admin){
             role = "ADMIN";
         } else if(user instanceof Manager){
             role = "MANAGER";
         }else if(user instanceof Employee){
             role = "EMPLOYEE";
         }
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    public Person getUser() {
        return user;
    }
}