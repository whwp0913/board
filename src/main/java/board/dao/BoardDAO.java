package board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import board.domain.BoardVO;
import board.util.BoardIO;

@Repository
public class BoardDAO {
	
	@Autowired
	private BoardIO boardIO;

	public void insert(BoardVO vo) {
		boardIO.createFile(vo);
	}

	public void delete(String id) {
		boardIO.deleteFile(id);
	}

	public void update(BoardVO vo) {
		boardIO.updateFile(vo);
	}

	public List<BoardVO> selectList(int page) {
		return boardIO.listFile(page);
	}
	
	public int count() {
		return boardIO.count();
	}

	public BoardVO select(String id) {
		return boardIO.selectFile(id);
	}

}
