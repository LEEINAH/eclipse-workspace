package mvc.vo;

public class MemberVo { // Vo : value Object 값을 담는 객체다 또는 DTO 라고도 한다

	private int midx;                  // DB 테이블에 있는 컬럼 이름과 동일하게 작성한다
	private String memberid;           // 바인딩 기술을 사용하기 위해 
	private String memberpwd;          // html input name명과 동일하게 맞춘다
	private String membername;    
	private String membergender;
	private String memberbirth;
	private String memberaddr;
	private String memberphone;
	private String memberemail;
	private String memberhobby;
	private String delyn;
	private String writeday;
	private String memberip;
	
	public int getMidx() {
		return midx;
	}
	public void setMidx(int midx) {
		this.midx = midx;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMemberpwd() {
		return memberpwd;
	}
	public void setMemberpwd(String memberpwd) {
		this.memberpwd = memberpwd;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public String getMembergender() {
		return membergender;
	}
	public void setMembergender(String membergender) {
		this.membergender = membergender;
	}
	public String getMemberbirth() {
		return memberbirth;
	}
	public void setMemberbirth(String memberbirth) {
		this.memberbirth = memberbirth;
	}
	public String getMemberaddr() {
		return memberaddr;
	}
	public void setMemberaddr(String memberaddr) {
		this.memberaddr = memberaddr;
	}
	public String getMemberphone() {
		return memberphone;
	}
	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}
	public String getMemberemail() {
		return memberemail;
	}
	public void setMemberemail(String memberemail) {
		this.memberemail = memberemail;
	}
	public String getMemberhobby() {
		return memberhobby;
	}
	public void setMemberhobby(String memberhobby) {
		this.memberhobby = memberhobby;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public String getMemberip() {
		return memberip;
	}
	public void setMemberip(String memberip) {
		this.memberip = memberip;
	}
	
}
// 깃 test
// pull test
// pull test2 
// 좀 돼라

