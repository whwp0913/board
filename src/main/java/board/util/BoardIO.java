package board.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import board.code.FileAction;
import board.domain.BoardVO;

public class BoardIO {

	private static final String PATH = "/Users/whwp0913/Board/";
	// private static final String PATH = "file:./data/board";
	private static final int LIST_SIZE = 10; // 목록 개수
	private static StringBuilder str = new StringBuilder();

	// idx check
	static {
		File file = new File(PATH + "post_idx");

		if (file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				str.append(br.readLine());
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// add idx or delete idx
	public static synchronized void postIDX(String id, FileAction action) {
		if (action == FileAction.create) {
			try (FileWriter fw = new FileWriter(PATH + "post_idx", true)) {
				str.append(id + "|");
				fw.write(id + "|");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (action == FileAction.delete) {
			try (FileWriter fw = new FileWriter(PATH + "post_idx", false)) {
				// 삭제할 board -> uuid|
				int startIdx = StringUtils.ordinalIndexOf(str, id, 1);
				int endIdx = startIdx+id.length()+1;

				str.delete(startIdx, endIdx);
				fw.write(str.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// add board file
	public static void createFile(BoardVO vo) {
		String id = UUID.randomUUID().toString();
		Gson gson = new Gson();

		try (FileWriter fw = new FileWriter(new File(PATH + id))) {
			vo.setId(id);
			fw.write(gson.toJson(vo));
			postIDX(id, FileAction.create);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// read board file
	public static BoardVO selectFile(String id) {
		BoardVO vo = new BoardVO();
		Gson gson = new Gson();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH + id))) {
			String str = br.readLine();
			vo = gson.fromJson(str, BoardVO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	// update board file
	public static void updateFile(BoardVO vo) {
		vo.setUpdate(new SimpleDateFormat("yyyy.MM.dd hh:mm").format(Calendar.getInstance().getTime()));
		Gson gson = new Gson();
		
		try (FileWriter fw = new FileWriter(new File(PATH + vo.getId()))) {
			fw.write(gson.toJson(vo));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// delete board file
	public static void deleteFile(String id) {
		File file = new File(PATH + id);
		if (file.exists()) {
			file.delete();
			postIDX(id, FileAction.delete);
		}
	}

	// list board file
	public static List<BoardVO> listFile(int page) {

		// data 역순정렬 (DESC)
		String temp = StringUtils.reverseDelimited(str.toString(), '|');

		// data validate
		if (temp.startsWith("|")) {
			temp = temp.substring(1);
		}
		if (!temp.endsWith("|")) {
			temp = temp + "|";
		}

		List<BoardVO> boardList = new ArrayList<>();
		int totalCount = count();

		int totalPageSize = totalCount / LIST_SIZE;
		if (totalCount % LIST_SIZE > 0) {
			totalPageSize += 1;
		}

		if (totalPageSize < page) {
			return Collections.emptyList();
		}

		int startIndex = StringUtils.ordinalIndexOf(temp, "|", (page - 1) * 10) + 1;
		if (page == 1) {
			startIndex = 0;
		}

		if (page * 10 < totalCount) {
			totalCount = page * 10;
		}

		System.out.println(temp);

		int endIndex = StringUtils.ordinalIndexOf(temp, "|", totalCount);

		System.out.println("totalCount ===>" + totalCount);
		System.out.println("endIndex ===> " + endIndex);

		String[] listFile = temp.substring(startIndex, endIndex).split("[|]");

		for (String i : listFile) {

			try (BufferedReader br = new BufferedReader(new FileReader(PATH + i))) {
				Gson gson = new Gson();
				BoardVO vo = gson.fromJson(br.readLine(), BoardVO.class);
				boardList.add(vo);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return boardList;
	}

	public static int count() {
		String data = str.toString();
		int count = StringUtils.countMatches(data, "|");
		if (count < 0) {
			count = 0;
		}
		return count;
	}
}
