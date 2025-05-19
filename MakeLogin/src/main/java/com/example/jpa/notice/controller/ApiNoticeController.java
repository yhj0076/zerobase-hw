package com.example.jpa.notice.controller;

import com.example.jpa.notice.entity.Notice;
import com.example.jpa.notice.execption.AlreadyDeletedException;
import com.example.jpa.notice.execption.DuplicateNoticeException;
import com.example.jpa.notice.execption.NoticeNotFundException;
import com.example.jpa.notice.model.NoticeDeleteInput;
import com.example.jpa.notice.model.NoticeInput;
import com.example.jpa.notice.model.NoticeModel;
import com.example.jpa.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
public class ApiNoticeController {

    private final NoticeRepository noticeRepository;


    /*
    @GetMapping("/api/notice")
    public String noticeString(){
        return "공지사항입니다.";
    }
    */

    /*
    @GetMapping("/api/notice")
    public NoticeModel notice() {

        LocalDateTime regDate = LocalDateTime.of(2021, 2, 8, 0, 0);

        NoticeModel notice = new NoticeModel();
        notice.setId(1);
        notice.setTitle("공지사항입니다.");
        notice.setContents("공지사항 내용입니다.");
        notice.setRegDate(regDate);

        return notice;
    }
    */

    /*
    @GetMapping("/api/notice")
    public List<NoticeModel> notice() {

        List<NoticeModel> noticeList = new ArrayList<>();

        noticeList.add(NoticeModel.builder()
                .id(1)
                .title("공지사항입니다.")
                .contents("공지사항내용입니다.")
                .regDate(LocalDateTime.of(2021, 1, 30, 0, 0))
                .build());
        noticeList.add(NoticeModel.builder()
                .id(2)
                .title("두번째 공지사항입니다.")
                .contents("두번째 공지사항내용입니다.")
                .regDate(LocalDateTime.of(2021, 1, 31, 0, 0))
                .build());

        return noticeList;
    }
    */

    @GetMapping("/api/notice")
    public List<NoticeModel> notice() {
        List<NoticeModel> noticeList = new ArrayList<>();

        return noticeList;
    }

    @GetMapping("/api/notice/count")
    public int noticeCount() {

        return 20;
    }


    /*
    @PostMapping("/api/notice")
    public NoticeModel addNotice(@RequestParam String title, @RequestParam String contents) {

        NoticeModel notice = NoticeModel.builder()
                .id(1)
                .title(title)
                .contents(contents)
                .regDate(LocalDateTime.now())
                .build();

        return notice;
    }
    */

    /*
    @PostMapping("/api/notice")
    public NoticeModel addNotice(NoticeModel noticeModel) {

        noticeModel.setId(2);
        noticeModel.setRegDate(LocalDateTime.now());

        return noticeModel;
    }*/

    /*
    @PostMapping("/api/notice")
    public NoticeModel addNotice(@RequestBody NoticeModel noticeModel) {

        noticeModel.setId(3);
        noticeModel.setRegDate(LocalDateTime.now());

        return noticeModel;
    }
    */

    /*
    @PostMapping("/api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contnets(noticeInput.getContents())
                .regDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);

        return notice;
    }
    */

    /*
    @PostMapping("/api/notice")
    public Notice addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .regDate(LocalDateTime.now())
                .hits(0)
                .likes(0)
                .build();

        Notice resultNotice = noticeRepository.save(notice);

        return resultNotice;
    }
    */

    @GetMapping("/api/notice/{id}")
    public Notice notice(@PathVariable Long id) {

        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        }

        return null;
    }

    /*
    @PutMapping("/api/notice/{id}")
    public void updateNotice(@PathVariable Long id
            , @RequestBody NoticeInput noticeInput) {

        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isPresent()) {
            notice.get().setTitle(noticeInput.getTitle());
            notice.get().setContents(noticeInput.getContents());
            notice.get().setUpdateDate(LocalDateTime.now());
            noticeRepository.save(notice.get());
        }

    }
    */

    @ExceptionHandler(NoticeNotFundException.class)
    public ResponseEntity<String> handlerNoticeNotFundException(NoticeNotFundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /*
    @PutMapping("/api/notice/{id}")
    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {

        //Optional<Notice> notice = noticeRepository.findById(id);
        //if (!notice.isPresent()) {
            //예외 발생
            throw new NoticeNotFundException("공지사항의 글이 존재하지 않습니다.");
        //}

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFundException("공지사항의 글이 존재하지 않습니다."));

        //공지사항 글이 있을때
        notice.setTitle(noticeInput.getTitle());
        notice.setContents(noticeInput.getContents());
        notice.setUpdateDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }
    */


    @PutMapping("/api/notice/{id}")
    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFundException("공지사항의 글이 존재하지 않습니다."));

        notice.setTitle(noticeInput.getTitle());
        notice.setContents(noticeInput.getContents());
        notice.setUpdateDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }


    @PatchMapping("/api/notice/{id}/hits")
    public void noticeHits(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFundException("공지사항의 글이 존재하지 않습니다."));

        notice.setHits(notice.getHits() + 1);

        noticeRepository.save(notice);
    }


    /*
    @DeleteMapping("/api/notice/{id}")
    public void deleteNotice(@PathVariable Long id) {

        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isPresent()) {
            noticeRepository.delete(notice.get());
        }
    }
    */


    /*
    @DeleteMapping("/api/notice/{id}")
    public void deleteNotice(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFundException("공지사항의 글이 존재하지 않습니다."));

        noticeRepository.delete(notice);
    }
    */

    @ExceptionHandler(AlreadyDeletedException.class)
    public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }

    @DeleteMapping("/api/notice/{id}")
    public void deleteNotice(@PathVariable Long id) {

        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFundException("공지사항의 글이 존재하지 않습니다."));

        if (notice.isDeleted()) {
            throw new AlreadyDeletedException("이미 삭제된 글입니다.");
        }

        notice.setDeleted(true);
        notice.setDeletedDate(LocalDateTime.now());

        noticeRepository.save(notice);
    }

    @DeleteMapping("/api/notice")
    public void deleteNoticeList(@RequestBody NoticeDeleteInput noticeDeleteInput) {

        List<Notice> noticeList = noticeRepository.findByIdIn(noticeDeleteInput.getIdList())
                .orElseThrow(() -> new NoticeNotFundException("공지사항의 글이 존재하지 않습니다."));

        noticeList.stream().forEach(e -> {
            e.setDeleted(true);
            e.setDeletedDate(LocalDateTime.now());
        });

        noticeRepository.saveAll(noticeList);
    }

    @DeleteMapping("/api/notice/all")
    public void deleteAll() {

        noticeRepository.deleteAll();

    }


    /*
    @PostMapping("/api/notice")
    public void addNotice(@RequestBody NoticeInput noticeInput) {

        Notice notice = Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .hits(0)
                .likes(0)
                .regDate(LocalDateTime.now())
                .build();

        noticeRepository.save(notice);
    }
    */


/*

    @PostMapping("/api/notice")
    public ResponseEntity<Object> addNotice(@RequestBody @Valid NoticeInput noticeInput
            , Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = new ArrayList<>();

            errors.getAllErrors().stream().forEach(e-> {
                responseErrors.add(ResponseError.of((FieldError)e));
            });

            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        noticeRepository.save(Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .hits(0)
                .likes(0)
                .regDate(LocalDateTime.now())
                .build());

        return ResponseEntity.ok().build();

    }
*/

    /*
    @PostMapping("/api/notice")
    public ResponseEntity<Object> addNotice(@RequestBody @Valid NoticeInput noticeInput
            , Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = new ArrayList<>();

            errors.getAllErrors().stream().forEach(e -> {
                responseErrors.add(ResponseError.of((FieldError) e));
            });

            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        noticeRepository.save(Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .hits(0)
                .likes(0)
                .regDate(LocalDateTime.now())
                .build());

        return ResponseEntity.ok().build();

    }
    */

    @GetMapping("/api/notice/latest/{size}")
    public Page<Notice> noticeLatest(@PathVariable int size) {

        Page<Notice> noticeList
                = noticeRepository.findAll(
                PageRequest.of(0, size, Sort.Direction.DESC, "regDate"));

        return noticeList;
    }

    @ExceptionHandler(DuplicateNoticeException.class)
    public ResponseEntity<?> handlerDuplicateNoticeException(DuplicateNoticeException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/notice")
    public void addNotice(@RequestBody NoticeInput noticeInput) {

        //중복체크
        LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1);
        int noticeCount = noticeRepository.countByTitleAndContentsAndRegDateIsGreaterThanEqual(
                noticeInput.getTitle()
                , noticeInput.getContents()
                , checkDate);
        if (noticeCount > 0) {
            throw new DuplicateNoticeException("1분이내에 등록된 동일한 공지사항이 존재합니다.");
        }

        noticeRepository.save(Notice.builder()
                .title(noticeInput.getTitle())
                .contents(noticeInput.getContents())
                .hits(0)
                .likes(0)
                .regDate(LocalDateTime.now())
                .build());
    }


}



















