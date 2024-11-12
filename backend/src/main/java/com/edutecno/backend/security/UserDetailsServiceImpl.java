package com.edutecno.backend.security;

import com.edutecno.backend.model.User;
import com.edutecno.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("From user details service: " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
//        List<String> roles = user.getRoles().getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).toList();
        // Lo importante que es esto, OJO, setear el enabled a true si no, no funciona
        user.setEnabled(true);
        System.out.println("Lo encontro " + user.getPassword());
        return UserDetailsImpl.build(user);
    }
}
