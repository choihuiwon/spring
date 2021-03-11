package board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.FileDTO;
import board.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;
	
	public BoardService(BoardMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public List<BoardDto> getBoardList(int pageNo, String mode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("mode", mode);
		return mapper.getBoardList(map);
	}

	public int getCount() {
		return mapper.getCount();
	}

	public BoardDto getBoardDto(int bno) {
		return mapper.getBoardDto(bno);
	}

	public int addCount(int bno) {
		return mapper.addCount(bno);
	}

	public List<CommentDto> getCommentList(int bno) {
		return mapper.getCommentList(bno);
	}

	public List<FileDTO> selectFileList(int bno) {
		return mapper.selectFileList(bno);
	}

	public int newBno() {
		return mapper.newBno();
	}
	
	public int insertBoard(BoardDto dto) {
		return mapper.insertBoard(dto);
	}

	public void insertFileList(ArrayList<FileDTO> fList) {
		for(int i=0; i<fList.size(); i++)
			mapper.insertFileList(fList.get(i));
	}

}
