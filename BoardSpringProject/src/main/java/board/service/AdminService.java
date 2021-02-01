package board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.MemberDTO;
import board.mapper.AdminMapper;

@Service
public class AdminService {
	private AdminMapper mapper;

	public AdminService(AdminMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<MemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public int adminUpdateMember(String id, String name, int age, int grade) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("age", age);
		map.put("grade", grade);
		return mapper.adminUpdateMember(map);
	}

	public int adminDeleteMember(String id) {
		return mapper.adminDeleteMember(id);
	}

	public List<MemberDTO> adminSearchMember(String kind, String search) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		return mapper.adminSearchMember(map);
	}
	
	
}
