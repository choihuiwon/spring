package board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import board.dto.CommentDto;
import board.mapper.CommentMapper;

@Service
public class CommentService {
	private CommentMapper mapper;

	public CommentService(CommentMapper mapper) {
		super();
		this.mapper = mapper;
	}

	public int insertComment(CommentDto dto) {
		return mapper.insertComment(dto);
	}

	public int commentLikeHate(int cno, String mode) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cno", cno);
		map.put("mode", mode);
		return mapper.commentLikeHate(map);
	}

	public List<CommentDto> getCommentListSortLike(int bno) {
		return mapper.getCommentListSortLike(bno);
	}

	public List<CommentDto> getCommentListSortHate(int bno) {
		return mapper.getCommentListSortHate(bno);
	}
}
