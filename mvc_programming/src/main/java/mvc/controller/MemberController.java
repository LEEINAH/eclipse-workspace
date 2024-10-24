package mvc.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvc.dao.MemberDao;
import mvc.vo.MemberVo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet("/MemberController") // 서블릿 : 자바로 만든 웹 페이지 (접속 주소 : /MemberController)
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String location; // 전역 변수 초기화 => 이동할 페이지
	
	public MemberController(String location) {
		this.location = location;
	}
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// 넘어온 모든 값은 여기에서 처리해서 분기한다 - conntroller 역할
		
		// System.out.println("값이 넘어오나요?");	
		
		// 전체 주소를 추출
		// String uri = request.getRequestURI();
		// System.out.println("uri = " + uri); // uri = /mvc_programming/member/memberJoinAction.aws
		
		// String[] location = uri.split("/");
		String paramMethod = ""; // 전송 방식이 sendRedirect면 S값, forword 방식이면 F
		String url = "";
		
		if (location.equals("memberJoinAction.aws")) { // 2번 방의 값이 memberJoinAction.aws 이면 if 구문 실행
			
			String memberId = request.getParameter("memberid");
			String memberPwd = request.getParameter("memberpwd");			
			// String memberPwd2 = request.getParameter("memberpwd2");	
			String memberName = request.getParameter("membername");			
			String memberEmail = request.getParameter("memberemail");
			String memberPhone = request.getParameter("memberphone");
			String memberAddr = request.getParameter("memberaddr");			
			String memberGender = request.getParameter("membergender");			
			String memberBirth = request.getParameter("memberbirth");
			
			//매개 변수에 인자값 대입해서 함수 호출
			String[] memberHobby = request.getParameterValues("memberhobby");
			String memberInHobby = "";
			for(int i = 0; i < memberHobby.length; i++) {
				memberInHobby = memberInHobby + memberHobby[i] + ",";
			} 
			
			MemberDao md = new MemberDao();
			int value = md.memberInsert(
							memberId,           // 객체 안에 생성해놓은 멤버 메소드를 호출 해서 값을 꺼낸다
							memberPwd, 
							memberName, 
							memberGender, 
							memberBirth, 
							memberAddr, 
							memberPhone, 
							memberEmail, 
							memberInHobby);
			
			// value값이 1이면 입력 성공 0이면 입력 실패다
			// 1이면 성공 했기 때문에 다른 페이지로 이동시키고 0이면 다시 회원가입 입력페이지로 간다
			
			String msg = "";
			
			HttpSession session = request.getSession(); // 세션 객체 활용
			
			if (value == 1) {                                      // index.jsp 파일은 web.xml 웹 설정 파일에 기본 등록 되어 있어서 생략 가능
				msg = "회원 가입 되었습니다.";
				session.setAttribute("msg", msg);
				
				url = request.getContextPath() + "/index.jsp"; // request.getContextPath() : 프로젝트 이름
				                                                   // 전송방식 sendRedirect는 요청 받으면 다시 그 쪽으로 가라고 지시하는 방법
			} else {
				msg = "회원 가입 오류 발생 하였습니다.";
				session.setAttribute("msg", msg);
				
		        url = request.getContextPath() + "/member/memberJoin.jsp"; // sendRediect 방식은 새롭게 다른 쪽으로 가라고 지시
			}
			paramMethod = "S"; // 밑에서 sendRedirct 방식으로 처리한다
			// System.out.println("msg : " + msg);
		} else if (location.equals("memberJoin.aws")) {
			// System.out.println("들어왔나?");
			
			url = "/member/memberJoin.jsp";
			paramMethod = "F"; // 포워드 방식 : 내부 안에서 넘겨서 토스 하겠다는 뜻 
			// 하단에서 메소드로 처리합니다.
			
		} else if (location.equals("memberLogin.aws")) {
			// System.out.println("들어왔나?");
			
			url = "/member/memberLogin.jsp";
			paramMethod = "F"; // 포워드 방식 : 내부 안에서 넘겨서 토스 하겠다는 뜻
		} else if (location.equals("memberLoginAction.aws")) {
			// System.out.println("memberLoginAction 들어 왔나?");
			
			String memberId = request.getParameter("memberid");
			String memberPwd = request.getParameter("memberpwd");
			
			MemberDao md = new MemberDao();
			MemberVo mv = md.memberLoginCheck(memberId, memberPwd);
			System.out.println("mv 객체가 생겼나요? : " + mv);
			
			if (mv == null) { 
				url = request.getContextPath() + "/member/memberLogin.aws"; // 해당 주소로 다시 가세요 (해당 하는 값이 없을 때)
				paramMethod = "S";
			} else {
				// 해당되는 로그인 사용자가 있으면 세션에 회원정보 담아서 메인으로 가라
				
				String mid = mv.getMemberid(); 
				int midx = mv.getMidx();
				String memberName = mv.getMembername();
				
				HttpSession session = request.getSession();     // 아이디 꺼내기 
				session.setAttribute("mid", mid);               // 회원번호 꺼내기
				session.setAttribute("midx", midx);
				session.setAttribute("memberName", memberName); // 이름 꺼내기
				
				url = request.getContextPath() + "/"; // 로그인 되었으면 메인으로 가세요
				paramMethod = "S";
			}
		} else if (location.equals("memberLogout.aws")) {
			// System.out.println("memberLogout");
			
			// 세션 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("mid");
			session.removeAttribute("midx");
			session.removeAttribute("memberName");
			session.invalidate();
			
			url = request.getContextPath() + "/";
			paramMethod = "S";
		} else if (location.equals("memberList.aws")) {
			// System.out.println("memberList.aws");
			
			// 1. 메소드 불러서 처리하는 코드를 만들어야 한다
			MemberDao md = new MemberDao(); // 객체 생성
			ArrayList<MemberVo> alist = md.memberSelectAll();
			
			request.setAttribute("alist", alist);
			
			// 2. 보여줄 페이지를 forword 방식으로 보여준다 (공유의 특성을 가지고 있다)
			url = "/member/memberList.jsp";
			paramMethod = "F";
		} else if (location.equals("memberIdCheck.aws")) {
			System.out.println("memberIdCheck.aws");
			
			String memberId = request.getParameter("memberId");
			
			MemberDao md = new MemberDao();
			int cnt = md.memberIdCheck(memberId);
			
			// System.out.println("cnt : " + cnt);
			
			PrintWriter out = response.getWriter();
			out.println("{\"cnt\":\"" + cnt + "\"}");
		}
		
		if (paramMethod.equals("F")) {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		} else if (paramMethod.equals("S")){
			response.sendRedirect(url);
		} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
