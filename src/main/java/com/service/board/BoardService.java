package com.service.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.board.Board;
import com.service.base.BaseService;
@Service
@Transactional
public class BoardService extends BaseService<Board> {

}
