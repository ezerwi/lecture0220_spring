<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${data.getNum()}번글 상세보기</title>
<style>
	table {
		border-style: solid;
		border-width: 5px;
		text-align: center;
	}
	tr th {
		border-style: solid;
		border-width: 2px;
		height: 60px;
	}
	td {
		border-style: solid;
		border-width: 1px;
	}
	#__text__ {
		width : 650px;
		height : 50px;
		border-width: 1px;
		border-style : dotted;
		text-align: center;
	}
</style>

</head>
<body>
	<table>
		<colgroup>
			<col width="150px">
			<col width="700px">
		</colgroup>
		
		<form action = "update.do" >
		<tr>
			<th>제 목</th>
			<td><input id="__text__" type = "text" value = ${data.getTitle() } name = "title" /></td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td><input id="__text__" type = "text" value ="${data.getAuthor() }" name = "author" /></td>
		</tr>
		
		<tr>
			<th>내 용</th>
			<td><textarea name = "content" cols = "94" rows = "30">${data.getContent() }</textarea></td>
		</tr>
		
		<tr>
			<td colspan = "2"><br><INPUT TYPE = "submit" VALUE = "수정하기">&nbsp;&nbsp;<INPUT TYPE = "BUTTON" VALUE = "목록으로" onclick="location.href = 'list.do'">&nbsp;&nbsp;<INPUT TYPE = "BUTTON" VALUE = "삭제" ONCLICK = "location.href = 'delete.do?num=${data.getNum() }'" /><br><br></td>
		</tr>
		</form>
	</table>
	
</body>
</html>

<%--
	글 상세보기 및 수정화면 출력
	
	수정된 내용을 UpdateActionController에게 전달
	
	수정완료와 목록으로 이동 기능 보유
	
 --%>