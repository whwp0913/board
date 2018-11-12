package board;


import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import board.dao.BoardDAO;
import board.domain.BoardVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardApplicationTests {
	
	@Autowired
	BoardDAO dao;

	@Test
	public void contextLoads() {
	}
	
	// file add test
	@Test
	public void insertTest()throws IOException {
		for(int i=120; i<300; i++) {
			
			BoardVO vo = new BoardVO();
			vo.setTitle("test"+i);
			vo.setContent("test"+i);
			vo.setWriter("test"+i);
			vo.setPassword("test"+i);
			dao.insert(vo);
		}
	}
	
	// file read test
	@Test
	public void readTest()throws IOException {
		System.out.println(dao.select("2b54b363-8860-496c-bf81-0b0810c04a23"));
	}
	
	// file update test
	@Test
	public void updateTest()throws IOException {
		BoardVO vo = new BoardVO();
		System.out.println(vo = dao.select("2b54b363-8860-496c-bf81-0b0810c04a23"));
		vo.setTitle("aaa");
		dao.update(vo);
		System.out.println(vo = dao.select("2b54b363-8860-496c-bf81-0b0810c04a23"));
	}
	
	// file delete test
	@Test
	public void deleteTest()throws Exception {
		String str = "2fc0d85e-60f4-470d-af0d-0a9230b9c9c5";
		dao.delete(str);
	}
	
	// file List test
	@Test
	public void listTest()throws Exception {
		
		
		System.out.println(dao.selectList(5));
		
		
		
	}
}
