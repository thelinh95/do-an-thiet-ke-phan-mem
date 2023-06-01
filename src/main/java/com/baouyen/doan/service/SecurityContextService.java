package com.baouyen.doan.service;

import com.baouyen.doan.entity.Partner;
import com.baouyen.doan.entity.User;
import com.baouyen.doan.repository.PartnerRepository;
import com.baouyen.doan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.Optional;

@Service
public class SecurityContextService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    public User getCurrentLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            //TODO check if principal is User. no need to convert from UserDetails
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();

            // we use role as authority.
            Optional<String> roleOpt = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst();
            if(roleOpt.isPresent()) {
                Optional<User> byUsernameAndRole = userRepository.findByUsernameAndRole(username, roleOpt.get());
                if(!byUsernameAndRole.isPresent()) {
                    throw new RuntimeException("Exception when get user login information");
                }

                return byUsernameAndRole.get();
            }
        }

        throw new RuntimeException("Exception when get user login information");
    }

    public Partner getCurrentPartner(){
        User currentLoginUser = this.getCurrentLoginUser();
        List<Partner> partners = partnerRepository.findByNameContainingIgnoreCase(currentLoginUser.getUsername());
        if(partners.isEmpty()) {
            throw new RuntimeException("Exception when get parter login information");
        }
        return partners.get(0);
    }
}
