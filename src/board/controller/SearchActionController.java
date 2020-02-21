/*
 * Controller interface 구현받음
 * 
 * 전달된 num 이용
 * DAO search()로 검색하고
 * 그 결과값을 ArrayList 객체에 담아 list.jsp에 전달   
 */

package board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import board.dao.BoardDAO;

public class SearchActionController implements Controller {

	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		ArrayList list = this.dao.search(searchType, searchText);
		ModelAndView mav = new ModelAndView("list");
		mav.addObject("list", list);
				
		return mav;
	}

}
