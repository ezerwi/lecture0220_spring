/*
 * 각 쿼리를 실제 수행하는 class
 * 
 * 생성자 통해 Context로부터 DB 접속
 * 조회 / 검색 / 수정 / 삭제 등 쿼리 수행하고
 * 결과 반환
 * 
 * 각 Controller에서 호출되는 method들 보유
 */

package board.dao;

/* SQL Query 관련 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/* Context , InitialContext 객체 관련; Context.xml <Context>  */
// lookup(JNDI명) ; 탐색기 검색과 유사 개념
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
// JNDI 방식에서 추가되는 것
// DataSource 객체 - getConnection()
// Context.xml <Resource>에서 설정한 내용 불러옴
import javax.sql.DataSource;

import board.command.BoardCommand;
// DTO(=Model) 객체
import board.dto.BoardDTO;

public class BoardDAO {

	// Context.xml <Resource type ="" >
	DataSource ds;

	// 생성자 : DataSource 얻기; InitialContext + JNDI name
	public BoardDAO() {
		try {
			// InitialContext ctx = new InitialContext() 과도 동일
			// InitialContext implements Context
			Context ctx = new InitialContext();

			// lookup ("java:comp/env/찾고자 하는 JNDI 이름")
			// JNDI = Context.xml <Resource name = "" >
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("SUCCESS_ds=__" + ds);

		} catch (NamingException e) {
			System.out.println("ERR_BoardDAO_InitialContext()__" + e.getMessage());
		}
	} // BoardDAO()

	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

		String sql = "SELECT * FROM SPRINGBOARD ORDER BY NUM DESC";
		try {
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO data = new BoardDTO();

				data.setNum(rs.getInt("num"));
				data.setAuthor(rs.getString("author"));
				data.setTitle(rs.getString("title"));
				data.setContent(rs.getString("content"));
				data.setDate(rs.getString("writeday"));
				data.setReadcnt(rs.getInt("readcnt"));

				list.add(data);

			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERR_list()__" + e.getMessage());
		}
		return list;

	} // list()
		// list()

	// SQL에서 sequence 안만들었기 때문에 필요한 Method
	public int getNewNum() {
		int newNum = 1;
		String q = "select max(num) from springboard";
		Connection conn;
		try {
			conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(q);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				newNum = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("ERR_getNewNum()__" + e.getMessage());
		}
		return newNum;
	} // getNewNum()

	/*
	 * 글 쓰기 Method 원래는 write(BoardCommand data) 가 정석이지만 여기서는 3개만 전달 받기 위해
	 * parameter 지정
	 */
	public void write(String author, String content, String title) {
		int newNum = this.getNewNum();
		System.out.println("__write()__newNum__" + newNum);

		String q = "INSERT INTO SPRINGBOARD (NUM, AUTHOR, TITLE, CONTENT) VALUES (?, ?, ?, ?)";
		System.out.println("__write()__query__" + q);

		try {
			Connection conn = this.ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, newNum);
			stmt.setString(2, author);
			stmt.setString(3, title);
			stmt.setString(4, content);
			stmt.executeUpdate();

			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("__ERR_write()__" + e.getMessage());
		}

	} // write()

	public BoardDTO retrieve(String num) {
		BoardDTO data = new BoardDTO();

		String q_select = "SELECT * FROM SPRINGBOARD WHERE NUM = ?";
		String q_readcnt = "UPDATE SPRINGBOARD SET READCNT = READCNT+1 WHERE NUM=?";

		try {
			Connection conn = this.ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(q_readcnt);
			stmt.setString(1, num);
			int update = stmt.executeUpdate();

			stmt = null;
			stmt = conn.prepareStatement(q_select);
			stmt.setString(1, num);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				data.setNum(rs.getInt("num"));
				data.setReadcnt(rs.getInt("readcnt"));
				data.setTitle(rs.getString("title"));
				data.setAuthor(rs.getString("author"));
				data.setContent(rs.getString("content"));
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("__ERR__retrieve()__" + e.getMessage());
		}

		return data;
	} // retrieve()

	public void update(BoardCommand data, String num) {

		String q = "UPDATE SPRINGBOARD SET AUTHOR = ?, TITLE = ?, CONTENT = ? WHERE NUM = ?";
		try {
			Connection conn = this.ds.getConnection();
			PreparedStatement st = conn.prepareStatement(q);
			st.setString( 1, data.getAuthor());
			st.setString( 2, data.getTitle());
			st.setString( 3, data.getContent());
			st.setString( 4, num);
			st.executeUpdate();
			
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("__ERR__update()__"+e.getMessage());
		}
	}

}
