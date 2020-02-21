/*
 * AbstractCommandController 상속
 * 
 * 전달된 수정 값들을 
 * DAO의 update()로 수정하고 list.jsp 호출
 */

package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import board.command.BoardCommand;
import board.dao.BoardDAO;

public class UpdateActionController extends AbstractCommandController {

	private BoardDAO dao;
	
	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		BoardCommand data = (BoardCommand) command;
		String num = request.getParameter("num");
//		System.out.println("___________num_____________"+num);
//		String author = data.getAuthor();
//		String title = data.getTitle();
//		String content = data.getContent();
		
		this.dao.update(data, num);
		return new ModelAndView("redirect:/list.do");
	}

}
