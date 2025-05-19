package com.example.jpa.user.service;

import com.example.jpa.board.model.ServiceResult;
import com.example.jpa.user.entity.User;
import com.example.jpa.user.model.*;

import java.util.List;

public interface PointService {

    ServiceResult addPoint(String email, UserPointInput userPointInput);

}
