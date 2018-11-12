package board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import board.domain.BoardVO;
import board.util.BoardIO;

@Repository
public class BoardDAO {

	public void insert(BoardVO vo) {
		BoardIO.createFile(vo);
	}

	public void delete(String id) {
		BoardIO.deleteFile(id);
	}

	public void update(BoardVO vo) {
		BoardIO.updateFile(vo);
	}

	public List<BoardVO> selectList(int page) {
		return BoardIO.listFile(page);
	}
	
	public int count() {
		return BoardIO.count();
	}

	public BoardVO select(String id) {
		return BoardIO.selectFile(id);
	}

}
