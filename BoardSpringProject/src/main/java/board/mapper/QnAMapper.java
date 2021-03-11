package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.QnADto;

@Mapper
public interface QnAMapper {

	List<QnADto> selectQnAList(HashMap<String, Object> map);

	int responseQnA(HashMap<String, Object> map);

	int readQnA(int qno);

	int sendQnA(QnADto dto);

}
