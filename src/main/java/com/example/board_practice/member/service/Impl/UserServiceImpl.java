package com.example.board_practice.member.service.Impl;

import com.example.board_practice.admin.dto.UserDto;
import com.example.board_practice.admin.mapper.UserMapper;
import com.example.board_practice.admin.model.UserParam;
import com.example.board_practice.member.entity.SiteUser;
import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.mail.MailSendService;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.service.UserService;
import com.example.board_practice.member.service.ValidateHandling;
import com.example.board_practice.member.type.RoleType;
import com.example.board_practice.member.type.UserStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ValidateHandling implements UserService {

    private final UserRepository userRepository;
    private final MailSendService mailSendService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void registerUser(UserRegisterDto registerDto) {
        SiteUser user = registerDto.toEntity();
        String encPassword = BCrypt.hashpw(registerDto.getPassword(), BCrypt.gensalt());
        user.setEmailAuthYn(false)
                .setPassword(encPassword)
                .setEmailAuthKey(UUID.randomUUID().toString())
                .setUserStatus(UserStatusType.MEMBER_STATUS_REQ);

        userRepository.save(user);

        String text = mailSendService.createRegisterTextMessage(registerDto.getUserId(), user.getEmailAuthKey());
        String subject = "[회원 가입] 이메일 인증을 완료해주세요.";
        mailSendService.sendMail(registerDto.getEmail(), subject, text);
    }

    @Override
    public boolean emailAuth(String emailAuthKey) {
        Optional<SiteUser> optionalUser = userRepository.findByEmailAuthKey(emailAuthKey);
        if (!optionalUser.isPresent()) {
            return false;
        }
        SiteUser user = optionalUser.get();

        if(user.isEmailAuthYn()) { // 이미 활성화가 된 상태라면
            return false;
        }

        user.setEmailAuthYn(true).
                setEmailAuthAt(LocalDateTime.now())
                .setRoleType(RoleType.USER)
                .setUserStatus(UserStatusType.MEMBER_STATUS_AVAILABLE);

        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDto> list(UserParam param) {
        long totalCount = userMapper.selectListCount(param);
        List<UserDto> list = userMapper.selectList(param);

        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for(UserDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - param.getPageStart() - i);
                i++;
            }
        }

        return list;
    }
}
