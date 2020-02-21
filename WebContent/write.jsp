<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WRITE.JSP</title>
<style>
	table {
		border-style: solid;
		border-width: 4px;
		text-align: center;
	}
	tr th td {
		border-style: solid;
		border-width: 1px;
		border-color: grey;
		
	}
	#text {
		width: 650px;
	}
	#submit_btn {
		width: 850px;
		height: 50px;
		cursor: pointer;
		background-color: grey;
		border-style: solid;
		color: white;
		font-weight: bolder;
	}
</style>
</head>
<body>
	<h1>글쓰기</h1>

	<form action="write.do">
		<table>
			<colgroup>
				<col width="150px">
				<col width="700px">
			</colgroup>

			<tr>
				<th>제목</th>
				<td><input id = "text" type="text" name="title" required="required"></td>
			</tr>

			<tr>
				<th>작성자</th>
				<td><input id = "text" type="text" name="author" required="required"></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea name = "content" cols = "90" rows="30"></textarea></td>
			</tr>
			
			<tr>
				<td colspan="2" ><br><input id = "submit_btn" type = "submit" value = "작성완료"><br></td>
			</tr>
		</table>
	</form>

</body>
</html>

<%--
	글 쓰기 화면 출력
	
	입력된 내용을 WriteActionController 에게 전달
	
	글 저장과 글 목록으로 이동하는 기능 보유
	
 --%>