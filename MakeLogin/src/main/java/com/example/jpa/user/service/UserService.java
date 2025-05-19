package com.example.jpa.user.service;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.*;

import java.util.List;

public interface UserService {

    UserSummary getUserStatusCount();
    List<User> getTodayUsers();
    List<UserNoticeCount> getUserNoticeCount();
    List<UserLogCount> getUserLogCount();

    /**
     * 좋아요를 가장 많은 한 사용자 목록 리턴
     */
    List<UserLogCount> getUserLikeBest();

    /**
     * 관심사용자 등록
     */
    ServiceResult addInterestUser(String email, Long id);


    /**
     * 관심사용자 삭제
     */
    ServiceResult removeInterestUser(String email, Long interestId);

}
