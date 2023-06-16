package com.weekend.cafe.config;

import com.weekend.cafe.Service.ManagerService;
import com.weekend.cafe.domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    ManagerService managerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Manager> optionalManager = managerService.getManagerById(username);
        if(optionalManager.isPresent()){
            Manager manager = optionalManager.get();
            CustomUserDetails customUserDetails = new CustomUserDetails();
            customUserDetails.setSeq(manager.getSeq());
            customUserDetails.setId(manager.getManagerId());
            customUserDetails.setPw(manager.getPw());

            int auth = manager.getAuth();
            Collection<GrantedAuthority> authority = new ArrayList<>();
            if(auth == 3) {
                authority.add(new SimpleGrantedAuthority("ADMIN"));
                authority.add(new SimpleGrantedAuthority("MANAGER"));
                authority.add(new SimpleGrantedAuthority("USER"));
            }
            else if(auth == 2) {
                authority.add(new SimpleGrantedAuthority("MANAGER"));
                authority.add(new SimpleGrantedAuthority("USER"));
            }
            else {
                authority.add(new SimpleGrantedAuthority("USER"));
            }
            customUserDetails.setAuthorities(authority);
            return customUserDetails;
        }
        return null;
    }
}
