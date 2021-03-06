package org.korea.mvc.service;

import org.korea.mvc.dao.MemberDAO;
import org.korea.mvc.dto.MemberDTO;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	private MemberDAO dao;
	
	public MemberService(MemberDAO dao) {
		this.dao = dao;
		System.out.println("MemberService 생성");
	}
	
	public MemberDTO login(String id, String passwd) {
		return dao.login(id, passwd);
	}
}
