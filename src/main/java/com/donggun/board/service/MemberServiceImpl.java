package com.donggun.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donggun.board.domain.Member;
import com.donggun.board.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepo;

	@Autowired
	public MemberServiceImpl(MemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}
	
	@Override
	public Member getMemberById(String id) {
		return memberRepo.findById(id).get();
	}
	
}
