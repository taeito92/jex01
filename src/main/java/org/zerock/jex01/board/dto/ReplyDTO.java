package org.zerock.jex01.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno;
    private Long bno;
    private String replyer;
    private String reply;
    private LocalDateTime replyDate;
    private LocalDateTime modDate;

}
