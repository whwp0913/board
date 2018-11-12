package board.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BoardVO {
	
	private String id;
	private String title;
	private String content;
	private String writer;
	private String password;
	private String sysdate;
	private String update;
	
	public BoardVO() {
		this.sysdate = new SimpleDateFormat("yyyy.MM.dd hh:mm").format(Calendar.getInstance().getTime());
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSysdate() {
		return sysdate;
	}
	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", sysdate=" + sysdate + ", update=" + update + ", title=" + title + ", content="
				+ content + ", writer=" + writer + ", password=" + password + "]";
	}		
}
