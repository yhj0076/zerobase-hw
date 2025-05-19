package com.example.jpa.board.repository;

import com.example.jpa.board.model.BoardTypeCount;
import com.example.jpa.user.model.UserLogCount;
import com.example.jpa.user.model.UserNoticeCount;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BoardTypeCustomRepository {

    private final EntityManager entityManager;


    public List<BoardTypeCount> getBoardTypeCount() {

        String sql = " select bt.id, bt.board_name, bt.reg_date, bt.using_yn "
                + ", (select count(*) from board b where b.board_type_id = bt.id) as board_count "
                + " from board_type bt ";

        Query nativeQuery = entityManager.createNativeQuery(sql);
        List<Object[]> results = nativeQuery.getResultList();
        List<BoardTypeCount> resultList = results.stream()
            .map(objects -> new BoardTypeCount(objects))
            .collect(Collectors.toList());



        return resultList;

    }

}
