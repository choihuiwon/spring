package board;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDto;
import board.dto.CommentDto;
import board.dto.FileDTO;
import board.service.BoardService;
import board.vo.PaggingVo;

@Controller
public class BoardController {
	private BoardService service;

	public BoardController(BoardService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("boardListView.do")
	public String boardListView(HttpServletRequest request) {
		
		// 페이징
		int pageNo = 1;
		if(request.getParameter("pageNo") != null)
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		// 페이징 정보
		int count = service.getCount();	// 전체 글 개수
		PaggingVo pageVo = new PaggingVo(count, pageNo);
		
		// mode
		String mode = "bno";
		if(request.getParameter("mode") != null) 
			mode = request.getParameter("mode");
		
		// 글목록
		List<BoardDto> list = service.getBoardList(pageNo, mode);
		
		request.setAttribute("list", list);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageVo", pageVo);
		request.setAttribute("mode", mode);
		
		return "board/board_list_view";
	}
	
	@RequestMapping("boardView.do")
	public String boardView(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 번호
		int bno = 0;
		if(request.getParameter("bno") != null)
			bno = Integer.parseInt(request.getParameter("bno"));
		else
			bno = (int)request.getAttribute("bno");
		// 게시글 조회
		BoardDto dto = service.getBoardDto(bno);
		
		try {
			if(dto == null) {	// 그사이에 게시글이 삭제되었을 경우
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('게시글이 존재하지 않습니다.');history.back();</script>");
			}
			else {
				// 조회수 증가
				int count = service.addCount(bno);
				// 댓글 목록 조회
				List<CommentDto> list = service.getCommentList(bno);
				// 파일 목록 조회
				List<FileDTO> fList = service.selectFileList(bno);
				
				request.setAttribute("dto", dto);
				request.setAttribute("list", list);
				request.setAttribute("fList", fList);
				
				return "board/board_view";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return null;
	}
	
	@RequestMapping("boardWriteView.do")
	public String boardWriteView(HttpServletResponse response, HttpSession session) {
		if(session.getAttribute("login") != null && (boolean)session.getAttribute("login") == true)
			return "board/board_write_view";
		else {
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append("<script>alert('로그인 후 이용하실 수 있습니다.');location.href='login.do';</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@RequestMapping("boardWrite.do")
	public String boardWrite(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 글번호 발급
		int bno = service.newBno();
		
		String title = request.getParameter("title");
		String writer = request.getParameter("writer"); 
		String content = request.getParameter("content");
		
		List<MultipartFile> fileList = request.getFiles("file");
		String path = "c:\\fileupload\\"+ writer+"\\";
		ArrayList<FileDTO> fList = new ArrayList<FileDTO>();
		
		for(MultipartFile mf : fileList) {
			String originalFileName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			if(fileSize == 0) continue;
			System.out.println("originalfileName : " + originalFileName);
			System.out.println("fileSize : " + fileSize);
			System.out.println(mf.getContentType());
			
			// 파일 업로드
			String safeFile = path + originalFileName;
			fList.add(new FileDTO(bno, writer, originalFileName));
			File parentPath = new File(path);
			if(!parentPath.exists()) parentPath.mkdirs();	// 경로 생성
			try {
				mf.transferTo(new File(safeFile));
				
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		service.insertBoard(new BoardDto(bno, title, writer, content));
		request.setAttribute("bno", bno);
		
		service.insertFileList(fList);
		
		return boardView(request, response);
	}
	
	@RequestMapping("imageLoad.do")
	public String imageLoad(HttpServletRequest request, HttpServletResponse response) {
		String writer = request.getParameter("writer");
		String file = request.getParameter("file");
		String type = file.substring(file.lastIndexOf(".")+1);
		response.setContentType("image/"+type);
		File path = new File("c:\\fileupload\\" + writer + "\\" + file);
		try {
			FileInputStream fis = new FileInputStream(path);
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buffer = new byte[1024*1024];
			while(true){
				int size = fis.read(buffer);//읽어온 바이트수
				if(size == -1) break;//더이상 읽을 내용이 없음
				sos.write(buffer,0,size);
				sos.flush();		
			}
			 
			sos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("filedownload.do")
	public String filedownload(HttpServletRequest request, HttpServletResponse response) {
		//다운로드할 파일명
		String fileName = request.getParameter("file");
		String writer = request.getParameter("writer");
		//다운로드할 파일 전체 경로
		String path = "c:\\fileupload\\" + writer + File.separator + fileName;
		System.out.println("다운로드할 파일 전체 경로 : "+path);
		
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			String encodingName = URLEncoder.encode(path,"utf-8");
			fileName = URLEncoder.encode(fileName, "utf-8");
			//다운로드시 나타낼 기본파일명
			// 첨부파일처럼 하려면 꼭 필요한 부분
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setContentLengthLong((int)file.length());
			
			//사용자에게 파일을 전송
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			//버퍼 생성
			byte[] buffer = new byte[1024*1024];
			
			while(true){
				int size = fis.read(buffer); //읽어온 바이트수
				if(size == -1) break;//더이상 읽을 내용이 없음
				bos.write(buffer,0,size);
				bos.flush();		
			}
			fis.close();
			bos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
