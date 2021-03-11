package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.CommentDto;

@Mapper
public interface CommentMapper {

	int insertComment(CommentDto dto);

	int commentLikeHate(HashMap<String, Object> map);

	List<CommentDto> getCommentListSortLike(int bno);

	List<CommentDto> getCommentListSortHate(int bno);

}
