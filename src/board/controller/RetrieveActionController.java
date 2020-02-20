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

public class RetrieveActionController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
