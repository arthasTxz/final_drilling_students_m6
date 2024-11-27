package com.edutecno.backend.service;

import com.edutecno.backend.dto.SignInRequestDto;
import com.edutecno.backend.dto.UserDto;
import com.edutecno.backend.dto.UserRegisterDto;
import com.edutecno.backend.model.AuthorityEnum;
import com.edutecno.backend.model.Role;
import com.edutecno.backend.model.User;
import com.edutecno.backend.repository.RoleRepository;
import com.edutecno.backend.repository.UserRepository;
import com.edutecno.backend.security.UserDetailsImpl;
import com.edutecno.backend.security.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private JwtUtils jwtUtils;

    private AuthenticationManager authenticationManager;


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, JwtUtils jwtUtils,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public String signIn(SignInRequestDto signInRequestDto) {
        System.out.println(signInRequestDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.username(), signInRequestDto.password())
        );
        System.out.println("Auth: " + authentication.isAuthenticated());
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).toList();


        return jwtUtils.generateToken(authentication);
    }

    @Override
    public User signUp(UserRegisterDto userRegisterDto) {

        Optional<Role> optionalRoleUser = roleRepository.findByName(AuthorityEnum.ROLE_CLIENT);
        List<Role> roles = new ArrayList<>();

        optionalRoleUser.ifPresent(roles::add);

        User user = mappeToUserRegistertoUser(userRegisterDto);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return null;
    }

    private UserDto mapperUserToUserDto(User user){
        return new UserDto(user.getName(), user.getUsername(), user.getEmail());
    }

    private User mappeToUserRegistertoUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setUsername(userRegisterDto.username());
        user.setPassword(passwordEncoder.encode(userRegisterDto.password()));
        user.setName(userRegisterDto.name());
        user.setEmail(userRegisterDto.email());
        user.setEnabled(true);
        return user;
    }
}
