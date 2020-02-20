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

}
