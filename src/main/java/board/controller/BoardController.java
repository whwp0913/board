package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.dao.BoardDAO;
import board.domain.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardDAO dao;

	@GetMapping("/write")
	public void write() {

	}

	@PostMapping("/write")
	public String postWrite(RedirectAttributes redirectAttr, BoardVO vo) {
		BoardVO board = vo;
		String result = "글이 등록되었습니다.";

		dao.insert(board);
		redirectAttr.addFlashAttribute("message", result);
		return "redirect:/board/list";
	}

	@GetMapping("/list")
	public void list(Model model, @RequestParam(defaultValue = "1", required = false) int page) {
		if (page < 1) {
			page = 1;
		}

		List<BoardVO> list = dao.selectList(page);
		int totalCount = dao.count();
		model.addAttribute("list", list);
		model.addAttribute("totalCount", totalCount);
	}

	@GetMapping("/read")
	public String read(RedirectAttributes redirectAttr, Model model, @RequestParam String id) {
		if (dao.select(id) != null) {
			model.addAttribute("board", dao.select(id));
		} else {
			redirectAttr.addFlashAttribute("message", "없는 게시물 입니다.");
			return "redirect:/board/list";
		}
		return "board/read";
	}

	@GetMapping("/modify")
	public void modify(Model model, @RequestParam int page, @RequestParam String id) {
		model.addAttribute("board", dao.select(id));
	}

	@PostMapping("/modify")
	public String postModify(RedirectAttributes redirectAttr, BoardVO vo, @RequestParam int page) {
		BoardVO board = dao.select(vo.getId()); // 원본 board
		String result = null;

		if (board.getPassword().equals(vo.getPassword())) {
			dao.update(vo);
			result = "변경 되었습니다.";
		} else {
			result = "비밀번호가 맞지 않습니다.";
		}
		redirectAttr.addFlashAttribute("message", result);

		return "redirect:/board/read?page=" + page + "&id=" + vo.getId();
	}

	@PostMapping("/delete")
	public String postDelete(RedirectAttributes redirectAttr, BoardVO vo) {
		BoardVO board = dao.select(vo.getId()); // 원본 board
		String result = null;

		if (board.getPassword().equals(vo.getPassword())) {
			dao.delete(board.getId());
			result = "삭제 되었습니다.";
		} else {
			result = "비밀번호가 맞지 않습니다.";
		}
		redirectAttr.addFlashAttribute("message", result);

		return "redirect:/board/list";
	}
}
