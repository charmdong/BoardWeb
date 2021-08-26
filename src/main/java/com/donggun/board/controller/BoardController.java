package com.donggun.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.donggun.board.domain.Board;
import com.donggun.board.domain.Search;
import com.donggun.board.security.SecurityUser;
import com.donggun.board.service.BoardService;

@Controller
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping(value = "/getBoardList")
	public String getBoardList(Model model, Search search) {
		if(search.getSearchCondition() == null) {
			search.setSearchCondition("TITLE");
		}
		
		if(search.getSearchKeyword() == null) {
			search.setSearchKeyword("");
		}
		
		Page<Board> boardList = boardService.getBoardList(search);
		
		model.addAttribute("boardList", boardList);
		
		return "board/getBoardList";
	}
	
	@GetMapping(value = "/getBoard") 
	public String getBoard(Board board, Model model) {
		Board findBoard = boardService.getBoard(board);
		
		model.addAttribute("board", findBoard);
		
		return "board/getBoard";
	}
	
	@GetMapping(value = "/{seq}")
	public String getBoardBySeq(@PathVariable Long seq, Board board, Model model) {
		Board findBoard = boardService.getBoardBySeq(seq);
		
		model.addAttribute("board", findBoard);
		
		return "board/getBoard";
	}
	
	@PostMapping
	public String registBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		
		return "redirect:getBoardList";
	}
	
	@GetMapping(value = "/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}
	
	@PostMapping(value = "/insertBoard") 
	@ResponseStatus(code = HttpStatus.FOUND)
	public String insertBoard(Board board, @AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		boardService.insertBoard(board);
		
		return "redirect:getBoardList";
	}
	
	@PostMapping(value = "/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		
		return "forward:getBoardList";
	}
	
	@PostMapping(value = "/{boardNo}/delete")
	public String deleteBoardByNo(Board board) {
		boardService.deleteBoard(board);
	
		return "forward:getBoardList";
	}
}
