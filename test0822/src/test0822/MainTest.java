package test0822;
import java.util.ArrayList;

public class MainTest {
	public static void main(String[] args) {
		
		// 제너릭을 통한 객체 타입 생성, new 연산자를 활용해서 생성자를 호출한다
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		
		BoardVo bv = new BoardVo(); // BoardVo 객체를 생성한다
		bv.setSubject("게시판 제목입니다.");
		bv.setContents("게시판 내용입니다.");
		bv.setBidx(1);
		
		BoardVo bv2 = new BoardVo(); // BoardVo 객체 두번째 생성
		bv2.setSubject("게시판 두번째 제목입니다.");
		bv2.setContents("게시판 두번째 내용입니다.");
		bv2.setBidx(2);
		
		alist.add(bv);
		alist.add(bv2);
		
		System.out.println("alist에 담긴 것은?" + alist);
		System.out.println("alist에 담긴 두번째 객체가 가지고 있는 제목은?" + alist.get(1).getSubject());
		
		String aa = "안녕";
		String bb = "하세요";
		
		String cc = aa + bb;
		
		System.out.println("cc값은 뭘까요?" + cc);
	}
}
