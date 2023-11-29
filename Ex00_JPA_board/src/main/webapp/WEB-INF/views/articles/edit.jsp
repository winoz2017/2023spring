<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <form class="container" action="/articles/update" method="post">
        <input name="id" type="hidden" value="${id}">
        <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" class="form-control" name="title" value="${article.title}">
        </div>
        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea class="form-control" rows="3" name="content">${article.content}</textarea>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        <a href="/articles/${id}">Back</a>
    </form>
</body>
</html>