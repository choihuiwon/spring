package board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.QnADto;
import board.mapper.QnAMapper;

@Service
public class QnAService {
	private QnAMapper mapper;

	public QnAService(QnAMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<QnADto> selectQnAList(String id, int pageNo, String grade) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pageNo", pageNo);
		map.put("grade", grade);
		return mapper.selectQnAList(map);
	}

	public int responseQnA(int qno, String res) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("qno", qno);
		map.put("res", res);
		return mapper.responseQnA(map);
	}

	public int readQnA(int qno) {
		return mapper.readQnA(qno);
	}

	public int sendQnA(QnADto dto) {
		return mapper.sendQnA(dto);
	}
	
	
}
