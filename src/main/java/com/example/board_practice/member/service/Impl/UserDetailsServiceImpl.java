package com.example.board_practice.member.service.Impl;

import com.example.board_practice.member.domain.SiteUser;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<SiteUser> optionalUser = userRepository.findByUserId(userId);

        if(!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("사용자가 존재하지 않습니다.");
        }
        SiteUser user = optionalUser.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if(user.getRoleType() == RoleType.GUEST) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(RoleType.GUEST.getValue()));
        } else {
            grantedAuthorityList.add(new SimpleGrantedAuthority(RoleType.USER.getValue()));
        }

        return new User(user.getUserId(), user.getPassword(), grantedAuthorityList);
    }
}
