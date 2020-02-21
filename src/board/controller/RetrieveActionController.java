/*
 * Controller interface 구현받음
 * 
 * 전달된 num을 이용하여 DAO retrieve()를 통해  
 * 해당 레코드만 DTO객체에 담아 retrieve() 호출
 */

package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class RetrieveActionController implements Controller {

	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String num = request.getParameter("num");
		System.out.println("__retrieve__num__"+num);
		
		BoardDTO data = this.dao.retrieve(num);
		
		ModelAndView mav = new ModelAndView("retrieve");
		mav.addObject("data", data);
		request.setAttribute("data", data);
		
		return mav;
	}

}
