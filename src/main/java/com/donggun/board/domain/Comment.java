package com.donggun.board.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"member", "board"})
@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long commentNo;

	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false) // update SQL 실행 시 해당 컬럼 제외
	private Date createDate = new Date();
	
	@Column(updatable = false)
	private Long cnt = 0L;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false) // FK
	private Member member;
	
	@ManyToOne
	@JoinColumn(name="SEQ", nullable=false, updatable=false) // FK
	private Board board;
	
	public void setMember(Member member) {
		this.member = member;
		member.getCommentList().add(this);
	}
	
	public void setBoard(Board board) {
		this.board = board;
		board.getCommentList().add(this);
	}
}
