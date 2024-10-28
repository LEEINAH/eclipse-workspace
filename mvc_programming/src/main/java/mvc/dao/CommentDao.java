package mvc.dao;

import java.sql.*;
import java.util.ArrayList;
import mvc.dbcon.Dbconn;
import mvc.vo.BoardVo;
import mvc.vo.CommentVo;
import mvc.vo.Criteria;
import mvc.vo.MemberVo;
import mvc.vo.SearchCriteria;


public class CommentDao {

	private Connection conn; // 전역적으로 연결 객체를 쓴다
	private PreparedStatement pstmt; // 쿼리를 실행하기 위한 구문 객체

	
	public CommentDao() { // 생성자를 만든다. 왜 ? DB 연결하는 Dbconn 객체 생성하려고. 생성해야 mysql 접속하니까
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
	}
	
	// 모든 댓글 보기
	public ArrayList<CommentVo> commentSelectAll(int bidx) {
		
		ArrayList<CommentVo> alist = new ArrayList<CommentVo>();
		
		String sql = "SELECT * FROM comment WHERE delyn='N' AND bidx=? ORDER BY cidx DESC"; 
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int cidx = rs.getInt("cidx");
				String ccontents = rs.getString("ccontents");
				String cwriter = rs.getString("cwriter");
				String writeday = rs.getString("writeday");
				
				CommentVo cv = new CommentVo();
				
				cv.setCcontents(ccontents);
				cv.setCwriter(cwriter);
				cv.setWriteday(writeday);
				alist.add(cv);
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
	
	// 게시물 생성하기
	public int commentInsert(CommentVo cv) {
		int value = 0;
		
		String cwriter = cv.getCwriter();
		String ccontents = cv.getCcontents();
		String csubject = cv.getCsubject();
		int bidx = cv.getBidx();
		int midx = cv.getMidx();
		String cip = cv.getCip();
		
		String sql = "insert into comment(csubject, ccontents, cwriter, bidx, midx, cip)"
				   + "value(null, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ccontents);
			pstmt.setString(2, cwriter);
			pstmt.setInt(3, bidx);
			pstmt.setInt(4, midx);
			pstmt.setString(5, cip);
			value = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {    // 각 객체도 소멸시키고 DB연결 끊는다
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return value;
	}
	
	// 게시물 삭제하기
	public int commentDelete(int bidx, String password) {
		
		int value = 0;

		
		
		return value;
	}
	

	
}
