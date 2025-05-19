package com.example.jpa.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class ApiBoardScrapController {


    private final BoardService boardService;


    /**
     75. 게시글의 스크랩을 추가하는 API를 기능해 보세요.
     */
    @PutMapping("/api/board/{id}/scrap")
    public ResponseEntity<?> boardScrap(@PathVariable Long id
        , @RequestHeader("F-TOKEN") String token ) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.scrap(id, email));
    }


    /**
     76. 게시글의 스크랩을 삭제하는 API를 기능해 보세요.
     */
    @DeleteMapping("/api/scrap/{id}")
    public ResponseEntity<?> deleteBoardScrap(@PathVariable Long id
        , @RequestHeader("F-TOKEN") String token ) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        return ResponseResult.result(boardService.removeScrap(id, email));

    }


}



















