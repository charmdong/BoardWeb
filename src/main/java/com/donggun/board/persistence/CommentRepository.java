package com.donggun.board.persistence;

import org.springframework.data.repository.CrudRepository;

import com.donggun.board.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
