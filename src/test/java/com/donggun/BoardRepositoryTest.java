package com.donggun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.donggun.board.domain.Board;
import com.donggun.board.domain.Member;
import com.donggun.board.domain.Role;
import com.donggun.board.persistence.BoardRepository;
import com.donggun.board.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void testInsert() {
		Member member1 = new Member();

		member1.setId("member");
		member1.setName("donggun");
		member1.setPassword(encoder.encode("member123"));
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);

		memberRepo.save(member1);

		Member member2 = new Member();

		member2.setId("admin");
		member2.setName("eunjung");
		member2.setPassword(encoder.encode("admin123"));
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);

		memberRepo.save(member2);

		for (int index = 1; index <= 13; index++) {
			Board board = new Board();

			board.setMember(member1);
			board.setTitle(member1.getName() + "가 등록한 게시글 " + index);
			board.setContent(member1.getName() + "가 등록한 게시글 " + index);

			boardRepo.save(board);
		}

		for (int index = 1; index <= 13; index++) {
			Board board = new Board();

			board.setMember(member2);
			board.setTitle(member2.getName() + "가 등록한 게시글 " + index);
			board.setContent(member2.getName() + "가 등록한 게시글 " + index);
			
			boardRepo.save(board);
		}
	}
	
//	@Test
//	public void testGetBoard() {
//		
//		Board board = boardRepo.findById(1L).get();
//		
//		System.out.println(board);
//		System.out.println(board.getMember());
//	}
	
//	@Test
//	public void testGetBoardList() {
//		
//		Member member = memberRepo.findById("member1").get();
//		
//		System.out.println(member.getName() + "가 등록한 게시글");
//		member.getBoardList().stream().forEach(board -> System.out.println("--->" + board));
//	}
}
