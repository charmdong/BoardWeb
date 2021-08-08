package com.donggun.board.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.donggun.board.domain.Member;

/**
 * User 클래스를 상속 받아 스프링 시큐리티에서 사용자 인증에 사용되는 UserDetails 구현체 클래스
 */
public class SecurityUser extends User {

	private static final long serialVersionUID = 1L;
	private Member member;
	
	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
}
