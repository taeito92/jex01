package org.zerock.jex01.board.security;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.jex01.board.config.BoardRootConfig;
import org.zerock.jex01.common.config.RootConfig;
import org.zerock.jex01.security.config.SecurityConfig;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
public class PasswordTests {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testEncode(){
        String str = "member1";
        String enStr = passwordEncoder.encode(str);

        log.warn(enStr);
    }
    /*
    $2a$10$YtOHG/ge4Xo4X3v0f3PTeOXySZl/AkKJ0b1rzDUaXQeHR4f1t/n9S
    $2a$10$1IvK53mf1DeGkb0G7vKBBedkrGrg/pqqLoN4/cgOH/.reLyvDgLvu
    */

    @Test
    public void insertMember() {

        String query = "insert into tbl_member (mid,mpw,mname) values ('v1','v2','v3');";

        for(int i = 0; i < 10; i++) {

            String mid = "user" + i; //user0
            String mpw = passwordEncoder.encode("pw" + i); //pw0 -> 암호화(Bcrypted)
            String mname = "유저" + i; //유저0

            String result = query;

            result = result.replace("v1", mid);
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
        boolean match = passwordEncoder.matches("member1", str);

        log.warn(match);
    }
}
