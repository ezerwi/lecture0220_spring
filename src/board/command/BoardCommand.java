/*
 * 사용자로부터 순수 입력받는 값만 처리해주는 Class
 * 
 * BoardDTO 와는 다른 역할
 * WriteActionController와 UpdateActionController에서 사용
 */

package board.command;

public class BoardCommand {

	private String author, title, content;

	public BoardCommand() {

	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
