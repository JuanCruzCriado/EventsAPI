package com.events.org.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.events.org.entity.Admin;
import com.events.org.repository.AdminRepository;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAdminUsr(username)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador no encontrado"));

        return User.withUsername(admin.getAdminUsr())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
}
