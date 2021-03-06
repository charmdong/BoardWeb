package com.donggun.board.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"member", "commentList"})
@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long seq;
	
	private String title;
	
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false) // update SQL 실행 시 해당 컬럼 제외
	private Date createDate = new Date();
	
	@Column(updatable = false)
	private Long cnt = 0L;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false) // FK
	private Member member;
	
	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER)
	private List<Comment> commentList = new ArrayList<Comment>();
	
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}
