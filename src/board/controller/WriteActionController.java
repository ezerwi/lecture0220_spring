/*
 * AbstractCommandController 상속
 * 
 * handle() 내에서 입력받은 값을
 * DAO의 write() 로 저장하고 list.jsp 호출
 */

package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.command.BoardCommand;
import board.dao.BoardDAO;

public class WriteActionController extends AbstractCommandController {

	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		BoardCommand data = (BoardCommand) command;
		/*
		 * Spring에서 아래와 같은 작업은 더이상 필요 없음
		 * 
		 */
		
		 String author = data.getAuthor();
		 String content = data.getContent();
		 String title = data.getTitle();
		
//		this.dao.write(data);
		this.dao.write(author, content, title);
		
		return new ModelAndView("redirect:/list.do");
		/*
		 * redirect 없이 ModelAndView("list.do")를 입력하면
		 * list.do.jsp로 이동하게 됨
		 */
		/*
		 * 동일 방식
		 * 1. Servlet Model2 방식 
		 * response.sendRedirect("list.jsp");
		 * 
		 *  2. ModelAndView
		 *  ModelAndView mav = new ModelAndView();
		 *  mav.setViewName("list");
		 *  또는
		 *  ModelAndView mav = new ModelAndView("list");
		 *  
		 *  return mav
		 */
	}

}
