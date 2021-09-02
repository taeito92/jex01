package org.zerock.jex01.board.domain;

import lombok.*;
import org.zerock.jex01.board.dto.BoardDTO;

import java.time.LocalDateTime;

//읽기전용,불변
//DB와 1:1로 대응하여 형성
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {

    private long bno;
    private String title,content,writer;
    private LocalDateTime regDate,modDate;

    public BoardDTO getDTO() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(bno)
                .title(title)
                .content(content)
                .writer(writer)
                .regDate(regDate)
                .modDate(modDate)
                .build();
        return boardDTO;
    }

    public void setBno(long bno) {
        this.bno = bno;
    }
}
