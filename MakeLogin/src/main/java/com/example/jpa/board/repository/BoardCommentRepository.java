package com.example.jpa.board.repository;

import com.example.jpa.board.entity.Board;
import com.example.jpa.board.entity.BoardComment;
import com.example.jpa.board.entity.BoardLike;
import com.example.jpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {


    List<BoardComment> findByUser(User user);

}
