package com.example.jpa.board.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jpa.board.entity.BoardType;
import com.example.jpa.board.model.*;
import com.example.jpa.board.service.BoardService;
import com.example.jpa.common.model.ResponseResult;
import com.example.jpa.notice.model.ResponseError;
import com.example.jpa.user.model.ResponseMessage;
import com.example.jpa.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ApiBoardController {


    private final BoardService boardService;


    /**
     * 61. 게시판타입을 추가하는 API를 작성해 보세요.
     * - 동일한 게시판 제목이 있는 경우 status:200, result:false, message에 이미 동일한 게시판이 존재한다는 메시지 리턴
     * - 게시판이름은 필수항목에 대한 부분 체크
     */
    @PostMapping("/api/board/type")
    public ResponseEntity<?> addBoardType(@RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다", responseErrors), HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.addBoard(boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }
        return ResponseEntity.ok().build();

    }

    /**
     * 62. 게시판타입명을 수정하는 API를 작성해 보세요.
     * - 게시판명이 동일할 경우 "수정할 이름이 동일한 게시판명 입니다." 리턴
     */
    @PutMapping("/api/board/type/{id}")
    public ResponseEntity<?> updateBoardType(@PathVariable Long id, @RequestBody @Valid BoardTypeInput boardTypeInput, Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = ResponseError.of(errors.getAllErrors());
            return new ResponseEntity<>(ResponseMessage.fail("입력값이 정확하지 않습니다", responseErrors), HttpStatus.BAD_REQUEST);
        }

        ServiceResult result = boardService.updateBoard(id, boardTypeInput);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }
        return ResponseEntity.ok().build();
    }


    /**
     * 63. 게시판타입 삭제하는 API를 작성해 보세요.
     * - 삭제시는 게시글이 있는 경우 삭제 안됨
     */
    @DeleteMapping("/api/board/type/{id}")
    public ResponseEntity<?> deleteBoardType(@PathVariable Long id) {

        ServiceResult result = boardService.deleteBoard(id);

        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());

    }


    /**
     * 64. 게시판타입의 목록을 리턴하는 API를 작성해 보세요.
     */
    @GetMapping("/api/board/type")
    public ResponseEntity<?> boardType() {

        List<BoardType> boardTypeList = boardService.getAllBoardType();

        return ResponseEntity.ok().body(ResponseMessage.success(boardTypeList));

    }


    /**
     * 65. 게시판타입의 사용여부를 설정하는 API를 작성해 보세요.
     */
    @PatchMapping("/api/board/type/{id}/using")
    public ResponseEntity<?> usingBoardType(@PathVariable Long id, @RequestBody BoardTypeUsing boardTypeUsing) {

        ServiceResult result = boardService.setBoardTypeUsing(id, boardTypeUsing);
        if (!result.isResult()) {
            return ResponseEntity.ok().body(ResponseMessage.fail(result.getMessage()));
        }

        return ResponseEntity.ok().body(ResponseMessage.success());
    }


    /**
     * 66. 게시판별 작성된 게시글의 개수를 리턴하는 API를 작성해 보세요.
     * - 현재 사용가능한 게시판에 대해서 게시글의 개수를 리턴함
     */
    @GetMapping("/api/board/type/count")
    public ResponseEntity<?> boardTypeCount() {

        List<BoardTypeCount> list = boardService.getBoardTypeCount();
        return ResponseEntity.ok().body(list);

    }


    /**
     * 67. 게시된 게시글을 최상단에 배치하는 API를 작성해 보세요.
     */
    @PatchMapping("/api/board/{id}/top")
    public ResponseEntity<?> boardPostTop(@PathVariable Long id) {
        ServiceResult result = boardService.setBoardTop(id, true);
        return ResponseEntity.ok().body(result);
    }


    /**
     * 68. 최상단에 게시된 게시글을 최상단에서 해제하는 API를 작성해 보세요.
     */
    @PatchMapping("/api/board/{id}/top/clear")
    public ResponseEntity<?> boardPostTopClear(@PathVariable Long id) {
        ServiceResult result = boardService.setBoardTop(id, false);
        return ResponseEntity.ok().body(result);
    }


    /**
     * 69. 게시글의 게시기간을 시작일과 종료일로 설정하는 API를 작성해 보세요.
     */
    @PatchMapping("/api/board/{id}/publish")
    public ResponseEntity<?> boardPeriod(@PathVariable Long id, @RequestBody BoardPeriod boardPeriod) {

        ServiceResult result = boardService.setBoardPeriod(id, boardPeriod);

        if (!result.isResult()) {
            return ResponseResult.fail(result.getMessage());
        }
        return ResponseResult.success();
    }


    /**
     * 70. 게시글의 조회수를 증가시키는 API를 작성해 보세요. 다만, 동일사용자 게시글 조회수 증가를 방지하는 부분에 대한 로직도 구현해 보세요.
     * - JWT 인증을 통과한 사용자에 대해서 진행
     */
    @PutMapping("/api/board/{id}/hits")
    public ResponseEntity<?> boardHits(@PathVariable Long id
            , @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.setBoardHits(id, email);
        if (result.isFail()) {
            return ResponseResult.fail(result.getMessage());
        }
        return ResponseResult.success();
    }

    /**
     71. 게시글에 대한 좋아요하기 기능을 수행하는 API를 작성해 보세요.
     */
    @PutMapping("/api/board/{id}/like")
    public ResponseEntity<?> boardLike(@PathVariable Long id
            , @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.setBoardLike(id, email);
        return ResponseResult.result(result);

    }


    /**
     72. 게시글의 좋아요한 내용을 취소하는 API를 작성해 보세요.
     */
    @PutMapping("/api/board/{id}/unlike")
    public ResponseEntity<?> boardUnLike(@PathVariable Long id
            , @RequestHeader("F-TOKEN") String token) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.setBoardUnLike(id, email);
        return ResponseResult.result(result);

    }


    /**
     73. 게시된 게시글에 대해서 문제가 있는 게시글을 신고하는 기능의 API를 작성해 보세요.
     */
    @PutMapping("/api/board/{id}/badreport")
    public ResponseEntity<?> boardBadReport(@PathVariable Long id
            , @RequestHeader("F-TOKEN") String token
            , @RequestBody BoardBadReportInput boardBadReportInput) {

        String email = "";
        try {
            email = JWTUtils.getIssuer(token);
        } catch (JWTVerificationException e) {
            return ResponseResult.fail("토큰 정보가 정확하지 않습니다.");
        }

        ServiceResult result = boardService.addBadReport(id, email, boardBadReportInput);
        return ResponseResult.result(result);
    }





}



















