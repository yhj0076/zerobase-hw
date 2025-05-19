package com.example.jpa.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class BoardBadReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //신고자정보
    @Column private long userId;
    @Column private String userName;
    @Column private String userEmail;

    //신고게시글정보
    @Column private long boardId;
    @Column private long boardUserId;
    @Column private String boardTitle;
    @Column private String boardContents;
    @Column private LocalDateTime boardRegDate;

    //신고내용
    @Column private String comments;

    @Column private LocalDateTime regDate;
}
