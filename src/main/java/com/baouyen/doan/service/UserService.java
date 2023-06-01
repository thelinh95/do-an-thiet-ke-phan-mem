package com.baouyen.doan.service;

import com.baouyen.doan.converter.UserConverter;
import com.baouyen.doan.dto.*;
import com.baouyen.doan.entity.User;
import com.baouyen.doan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        User user = userOpt.get();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    @PostConstruct
    public void createAdminUser() {
//        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
//        }
    }

    public boolean authenticate(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return passwordEncoder.matches(password, userOpt.get().getPassword());
        }
        return false;
    }

    public Page<UserDto> searchUser(SearchUserRequest request) {
        String name = request.getName();

        Paginator paginator = request.getPaginator();
        int page = paginator.getPage();
        int size = paginator.getSize();

        Pageable pageable = new PageRequest(page, size);

        Page<User> result;
        if (name != null) {
            result = userRepository.findByUsernameContainingIgnoreCase(name, pageable);
        } else {
            result = userRepository.findAll(pageable);
        }

        return result.map(c -> userConverter.entityToDto(c));
    }
}

