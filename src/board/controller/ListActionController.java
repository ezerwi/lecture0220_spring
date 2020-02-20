/*
 * Controller interface 구현받음
 * 
 * handleRequest() method 내에서 
 * DAO의 list() 결과 값을 list.jsp에게 ArrayList로 전달
 */

package board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

// Controller 상속받는 이유 : 요청 받아서 처리하기 위함
public class ListActionController implements Controller {
	
	
	private BoardDAO dao;

	public void setDao(BoardDAO dao) {
		this.dao = dao;
		System.out.println("SUCCESS_call_setDao()__"+dao);
	}

	/*
	 * dao 는 board-servlet.xml <bean name = "list.do"~~> <property ~~~>에 의해 주입
	 *
	 */
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		System.out.println("__CALL__ListActionController___");
		
		ArrayList<BoardDTO> list = dao.list();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		// list.jsp
		// ViewResolver에서 .jsp 추가했기 때문에 안써도 됨
		
		mav.addObject("list", list);
		// servlet에서 request.setAttribute()와 동일한 역할
		
		return mav;
		// DispatcherServlet에 전달
	}

}
