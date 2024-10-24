package mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mvc.dbcon.Dbconn;
import mvc.vo.MemberVo;

public class MemberDao { // MVC 방식으로 가기 전에 첫 번째 model1 방식
	
	private Connection conn; // 전역 변수로 사용. 페이지 어느곳에서도 사용 할 수 있다.
	private PreparedStatement pstmt;
	
	// 생성자를 통해서 db 연결해서 메소드 사용
	public MemberDao() {
		Dbconn dbconn = new Dbconn(); // DB 객체 생성
		conn = dbconn.getConnection(); // 메소드 호출해서 연결 객체를 가져온다
	}
	
	// public : 어디서나 접근 가능
	// int : 리턴값 타입 
	public int memberInsert(String memberId, String memberPwd, String memberName, String memberGender,
						     String memberBirth, String memberAddr, String memberPhone, String memberEmail, String memberInHobby) {

		int value = 0; // 메소드 지역 변수 (결과 값을 담는다)
		String sql = "";
		// PreparedStatement pstmt = null; // 쿼리 구문 클래스 선언
		
		try {
			
			sql = "insert into member(memberid,memberpwd,membername,membergender,memberbirth,memberaddr,"
				   + "memberphone,memberemail,memberhobby) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberId);        // 문자형 메소드 사용  // 숫자형은 setInt       
			pstmt.setString(2, memberPwd);       // 문자형 메소드 사용    
			pstmt.setString(3, memberName);      // 문자형 메소드 사용   
			pstmt.setString(4, memberGender);    // 문자형 메소드 사용   
			pstmt.setString(5, memberBirth);     // 문자형 메소드 사용   
			pstmt.setString(6, memberAddr);      // 문자형 메소드 사용   
			pstmt.setString(7, memberPhone);     // 문자형 메소드 사용   
			pstmt.setString(8, memberEmail);     // 문자형 메소드 사용   
			pstmt.setString(9, memberInHobby);   // 문자형 메소드 사용   
			
			value = pstmt.executeUpdate(); // 구문 객체 실행 하면 성공 시 1 실패 시 0 리턴
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {  // try를 했던 catch를 했던 꼭 실행해야하는 영역
			// 객체를 사라지게 하고
			// DB 연결 끊기
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	// 로그인을 통해서 회원 정보를 담아오는 메소드이다.
	public MemberVo memberLoginCheck(String memberId, String memberPwd) {
		MemberVo mv = null;
		String sql = "SELECT *\r\n"
				+ "FROM member\r\n"
				+ "WHERE memberid = ?\r\n"
				+ "AND memberpwd = ?";
		ResultSet rs = null; // DB에서 결과 데이터를 받아오는 전용 클래스 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			
			if (rs.next() == true) { // 커서가 이동해서 값이 있으면 (if (rs.nest()) 와 같은 표현)
				String memberid = rs.getString("memberid"); // 결과 값에서 아이디 값을 뽑는다
				int midx = rs.getInt("midx");              // 결과 값에서 회원 번호를 뽑는다
				String membername = rs.getString("membername");				
				mv = new MemberVo(); // 화면에 가지고 갈 데이터를 담을 VO 객체 생성
				mv.setMemberid(memberid); // 옮겨 담는다
				mv.setMidx(midx); 
				mv.setMembername(membername);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return mv;
	}
	
	public ArrayList<MemberVo> memberSelectAll() {
		
		ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
		String sql = "SELECT * FROM MEMBER WHERE delyn='N' ORDER BY midx DESC";
		ResultSet rs = null; // DB 값을 가져오기 위한 전용 클래스
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 커서가 다음으로 이동해서 첫 글이 있느냐 물어보고 true면 진
				int midx = rs.getInt("midx");
				String memberId = rs.getString("memberid");
				String memberName = rs.getString("membername");
				String memberGender = rs.getString("membergender");
				String writeday = rs.getString("writeday");
				
				MemberVo mv = new MemberVo(); // 첫 행부터 mv에 옮겨 담기
				mv.setMidx(midx);
				mv.setMemberid(memberId);
				mv.setMembername(memberName);
				mv.setMembergender(memberGender);
				mv.setWriteday(writeday);
				alist.add(mv);                // ArrayList 객체에 하나씩 추가한다
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return alist;
	}
	
	public int memberIdCheck(String memberId) {
		String sql = "SELECT COUNT(*) AS cnt\r\n"
				+ "FROM MEMBER\r\n"
				+ "WHERE memberid = ?";
		ResultSet rs = null; // DB에서 결과 데이터를 받아오는 전용 클래스 
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 커서가 이동해서 값이 있으면 (if (rs.nest()) 와 같은 표현)
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return cnt;
	}
}
