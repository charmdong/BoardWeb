package com.donggun.board.service;

import org.springframework.data.domain.Page;

import com.donggun.board.domain.Board;
import com.donggun.board.domain.Search;

public interface BoardService {

	void insertBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);

	Board getBoard(Board board);

	Page<Board> getBoardList(Search search);

}