package org.zerock.jex01.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.jex01.security.domain.Member;
import org.zerock.jex01.security.dto.MemberDTO;
import org.zerock.jex01.security.mapper.MemberMapper;


@Log4j2
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override //userName 이 id
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //예외 던지기 -> 유저를 못찾을 경우

        log.warn("==========================customUserDetailsService====================================");
        log.warn("==========================customUserDetailsService====================================");
        log.warn("==========================customUserDetailsService====================================");
        log.warn(username);
        log.warn(memberMapper);
        log.warn("==========================customUserDetailsService====================================");
        log.warn("==========================customUserDetailsService====================================");
        log.warn("==========================customUserDetailsService====================================");

        Member member = memberMapper.findByMid(username);

        if (member == null) {
            throw new UsernameNotFoundException("NOT EXIST"); //user가 없는 경우 예외를 던짐.
        }


        //result에 모든 회원 데이터를 담을 수 있게 됨.
        User result = new MemberDTO(member);

        /*
        String[] authrities = member.getRoleList().stream().map(memberRole -> memberRole.getRole()).toArray(String[]::new); //새로운 String 의 배열을 만들어줌

        //다운캐스팅 필요
        User result = (User) User.builder()
                .username(username)
                .password(member.getMpw())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authrities)
                //.authorities("ROLE_MEMBER") //config에서 role주는 것이 아니라 권한을 주는 것이므로 ROLE_가 필요함.
                .build();
        */
        return result;
    }


}
