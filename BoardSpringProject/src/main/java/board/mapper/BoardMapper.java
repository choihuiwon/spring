package board.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.FileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDto> getBoardList(HashMap<String,Object> map);

	int getCount();

	BoardDto getBoardDto(int bno);

	int addCount(int bno);

	List<CommentDto> getCommentList(int bno);

	List<FileDTO> selectFileList(int bno);

	int newBno();

	int insertBoard(BoardDto dto);

	void insertFileList(FileDTO dto);
	
}
