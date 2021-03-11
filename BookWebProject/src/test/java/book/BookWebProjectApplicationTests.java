package book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class BookWebProjectApplicationTests {

	@Autowired
	BookMapper mapper;
	
	@DisplayName("도서 등록 테스트")
	@Test
	void insertBookTest() {
		BookDTO dto = new BookDTO("89124561234", "자바프로그래밍", "홍길동", "J테스트", "2020-02-19");
		try {
			int count = mapper.insertBook(dto);
			if(count == 1)
				System.out.println("데이터 추가 성공");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@DisplayName("도서 검색 테스트")
	@Test
	void searchBookTest() {
		String str = "자바";
		List<BookDTO> list = mapper.selectBook(str);
		assertNotEquals("데이터 조회 실패", list.size(), 0);		// 해당하는 도서가 없으면 메시지 출력
	}
	
	@DisplayName("도서 삭제 테스트")
	@Test
	void deleteBookTest() {
		String str = "89124561234";
		int count = mapper.deleteBook(str);
		assertEquals("데이터 삭제 실패", count, 1);
	}
}
