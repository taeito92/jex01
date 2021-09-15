package org.zerock.jex01.board.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.common.config.RootConfig;
import org.zerock.jex01.security.config.SecurityConfig;
import org.zerock.jex01.security.domain.Member;
import org.zerock.jex01.security.mapper.MemberMapper;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
public class PasswordTests {

    @Autowired //test코드에서는 Autowired 필요
    PasswordEncoder passwordEncoder;

    @Autowired(required = false) //지금은 disabled 해놓아서 에러가 안나오지만 원래는 에러 나옴.
    MemberMapper memberMapper;

    @Test
    public void testMember() {
        String mid = "admin9";

        Member member = memberMapper.findByMid(mid);

        log.warn("-----------------------------------------");
        log.warn(member);
    }


    @Test
    public void testEncode(){
        String str = "member1";
        String enStr = passwordEncoder.encode(str);

        log.warn(enStr);
    }
    /*
    $2a$10$YtOHG/ge4Xo4X3v0f3PTeOXySZl/AkKJ0b1rzDUaXQeHR4f1t/n9S
    $2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu //현재 member1이 암호화됨
    */

    @Test
    public void insertMember() {

        String query = "insert into tbl_member (mid,mpw,mname) values ('v1','v2','v3');";

        for(int i = 0; i < 10; i++) {

            String mid = "user" + i; //user0
            String mpw = passwordEncoder.encode("pw" + i); //pw0 -> 암호화(Bcrypted)
            String mname = "유저" + i; //유저0

            String result = query;

            result = result.replace("v1", mid); //replace => 말그대로 대체하는 메서드
            result = result.replace("v2", mpw);
            result = result.replace("v3", mname);

            System.out.println(result);
        }

    }

    @Test
    public void insertAdmin() {

        String query = "insert into tbl_member (mid,mpw,mname) values ('v1','v2','v3');";

        for(int i = 0; i < 10; i++) {

            String mid = "admin" + i; //admin0~
            String mpw = passwordEncoder.encode("pw" + i); //pw0 -> 암호화(Bcrypted)
            String mname = "관리자" + i; //관리자0~

            String result = query;

            result = result.replace("v1", mid);
            result = result.replace("v2", mpw);
            result = result.replace("v3", mname);

            System.out.println(result);
        }

    }

    @Test
    //우리가 입력한 패스워드가 실제로 그 아이디에 해당 패스워드가 맞는지 물어보는 테스트코드
    public void testDecode() {
        String str = "$2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu";
        boolean match = passwordEncoder.matches("member1", str); //match는 말그대로 비교하는 메서드

        log.warn(match);
    }

    @Test
    public void insertMemberRole() {

        log.warn("insertMemberRole start ...............................");

        String sql = "insert into tbl_member_role (mid, role) values ('%s', '%s');";

        for(int i = 0; i < 10; i++) {
            String result = String.format(sql, "user" + i, "ROLE_MEMBER");

            System.out.println(result);
        }

    }

    @Test
    public void insertAdminRole() {

        log.warn("insertAdminRole start ...............................");

        String sql = "insert into tbl_member_role (mid, role) values ('%s', '%s');";

        for(int i = 0; i < 10; i++) {
            String result = String.format(sql, "admin" + i, "ROLE_MEMBER");

            System.out.println(result);

            String result2 = String.format(sql, "admin" + i, "ROLE_ADMIN");

            System.out.println(result2);
        }
    }




}
