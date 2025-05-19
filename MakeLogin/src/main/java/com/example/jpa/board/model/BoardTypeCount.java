package com.example.jpa.board.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardTypeCount {

    private long id;
    private String boardName;
    private LocalDateTime regDate;
    private boolean usingYn;
    private long boardCount;

    public BoardTypeCount(Object[] arrObj) {
        this.id = ((BigInteger) arrObj[0]).longValue();
        this.boardName = (String)arrObj[1];
        this.regDate = ((Timestamp) arrObj[2]).toLocalDateTime();
        this.usingYn = (Boolean)arrObj[3];
        this.boardCount = ((BigInteger)arrObj[4]).longValue();
    }

    public BoardTypeCount(BigInteger id, String boardName, Timestamp regDate, Boolean usingYn, BigInteger boardCount) {
        this.id = id.longValue();
        this.boardName = boardName;
        this.regDate = regDate.toLocalDateTime();
        this.usingYn = usingYn;
        this.boardCount = boardCount.longValue();
    }


}
