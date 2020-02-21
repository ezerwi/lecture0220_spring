/*
 * Controller interface 구현받음
 * 
 * 전달된 num 이용
 * DAO delete()로 삭제하고 list.jsp 호출  
 * 
 */

package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class DeleteActionController implements Controller {

	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		
		String num = request.getParameter("num");
		this.dao.delete(num);
		
		return new ModelAndView ("redirect:/list.do");
	}

}
