package com.donggun.board.persistence;

import org.springframework.data.repository.CrudRepository;

import com.donggun.board.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
