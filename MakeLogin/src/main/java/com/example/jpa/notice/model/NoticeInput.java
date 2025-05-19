package com.example.jpa.notice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NoticeInput {

    @Size(min = 10, max = 100, message = "제목은 10-100자 사이의 값입니다.")
    @NotBlank(message = "제목은 필수 항목입니다.")
    private String title;

    @Size(min = 50, max = 1000, message = "제목은 10-100자 사이의 값입니다.")
    @NotBlank(message = "내용은 필수 항목입니다.")
    private String contents;

}
