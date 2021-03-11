package board.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import board.dto.MemberDTO;
import board.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		super();
		this.mapper = mapper;
	}

	// 로그인
	public MemberDTO login(String id, String pass) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		return mapper.login(map);
	}

	// 회원정보 수정을 위한 회원정보 가져오기
	public MemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}

	public int memberUpdateAction(String id, String pass, String name, int age) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", pass);
		map.put("name", name);
		map.put("age", age);
		return mapper.memberUpdateAction(map);
	}

	public int insertLog(String log_date, int code_number, String message) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("log_date", log_date);
		map.put("code_number", code_number);
		map.put("message", message);
		return mapper.insertLog(map);
	}
	
}
