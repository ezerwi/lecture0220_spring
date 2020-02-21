<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*, board.dto.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BOARDLIST</title>

<style type="text/css">

	table {
		border-style: solid;
		border-width: 2px;
	}
	tr {
		height : 50px
	}
	input {
		height : 37px;
	}
	select {
		height : 37px;
		width: 120px;
		text-align-last : "center";
		direction : inherit;
	}
	option {
		
	}
	#selectone {
		text-align : "center";
		height: 37px;
		width : 200px;
		border-width: 2px;
		border-style : double;
		border-color : gray;
		background-color: white;
		cursor: pointer;
	}
</style>
</head>
<body>
	<H1>XML 성공~!</H1>
	
	<table border = "1">
	
	<colgroup>
		<col width = "50px">
		<col width = "300px">
		<col width = "200px">
		<col width = "300px">
		<col width = "100px">
	</colgroup>
	
	<tr>
	<td colspan = "5" align = "center"> ***게시판 목록*** <input type = "button" value = "글쓰기" onclick = "location.href='writeui.do'" /></td>
	</tr>
	
	<tr align = "center">
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
	</tr>
	
	
	<c:forEach var="one" items="${list}">
		<tr align="CENTER" >
		<td>${one.getNum() }</td>
		<td><input id = "selectone" TYPE="button" VALUE = "${one.getTitle() }" onclick="location.href = 'retrive.do?num='+${one.getNum()}">	</td>
		<td>${one.getAuthor()}</td>
		<td>${one.getDate().substring(0,10) }</td>
		<td>${one.getReadcnt() }</td>
		</tr>
	</c:forEach>
	
	<tr>
	
	<td colspan="5" align="center" >
	<form action="search.do" method="GET" > 
	<select name = "searchType" size = "1" >
		<option value="title">제목</option>
		<option value="author">작성자</option>
		<option value="content">내용</option>
	</select>
	
	<input type = "text" name = "searchText" /><input type = "submit" value = "검색" />
	</form>
	</td>
	</tr>
	</table>
	
	<P>
	
</body>
</html>

<%--
	글 목록 및 검색 결과 출력
	ListActionController로부터 전달된 ArrayList 객체를
	
	1. ArrayList 객체가 null 인지 확인
	2. null 이 아닌 경우 Iterator 객체 추출
	3. 전달된 각 데이터를 하나씩 추출
	4. 추출될 때마다 DTO 객체로 형변환
	5. <TABLE></TABLE> 내 한 줄씩 출력
	
	제목은 "retrieve.do?num=글번호" 형태로 링크 설정
	
	글 목록 중 하나를 선택하면 해당 글 번호를 RetrieveActionController에게 전달
 --%>