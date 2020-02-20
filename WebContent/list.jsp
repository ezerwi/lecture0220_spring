<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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