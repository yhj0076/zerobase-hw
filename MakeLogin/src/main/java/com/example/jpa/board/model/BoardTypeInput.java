package com.example.jpa.board.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardTypeInput {

    @NotBlank(message = "게시판 제목은 필수 항목입니다.")
    private String name;


}
