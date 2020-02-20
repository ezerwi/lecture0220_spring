/*
 * Controller interface 구현받음
 * 
 * 전달된 num 이용
 * DAO search()로 검색하고
 * 그 결과값을 ArrayList 객체에 담아 list.jsp에 전달   
 */

package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SearchActionController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
