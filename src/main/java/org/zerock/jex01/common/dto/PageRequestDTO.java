package org.zerock.jex01.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default //값이 없다면 기본적으로 값을 설정
    private int page = 1;

    @Builder.Default
    private int size = 10;

    //검색하기위한 컨텐츠 type과 검색하려는 keyword
    private String type;
    private String keyword;

    //mybatis는 getter가 내장되어있어서 get이 붙은 메서드를 가져올 수 있음 getSkip -> skip으로
    public int getSkip() {
        return (page - 1) * size; //skip해야하는 int를 구하는 메서드
    }

    public String[] getArr() {
        return type == null? new String[]{} : type.split("");
    }

}
