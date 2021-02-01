package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.MemberDTO;

@Mapper
public interface AdminMapper {

	List<MemberDTO> selectAllMember();

	int adminUpdateMember(HashMap<String, Object> map);

	int adminDeleteMember(String id);

	List<MemberDTO> adminSearchMember(HashMap<String, Object> map);
	
}
