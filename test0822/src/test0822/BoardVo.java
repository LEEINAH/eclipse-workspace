package test0822;

public class BoardVo { // Board(게시판) Vo(value Object)   같은 의미로 DTO(Data transer Object)

	private String subject; // 게시판 제목을 담는 멤버변수 (멤버변수는 초기화 하지 않아도 자동 초기화 된다) 
	private String contents; // 게시판의 내용을 담는 변수
	private int bidx; // 게시판 자동 생성 고유 번호
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getBidx() {
		return bidx;
	}
	public void setBidx(int bidx) {
		this.bidx = bidx;
	}
	
	
	
	
	
	
	
	
	
}
