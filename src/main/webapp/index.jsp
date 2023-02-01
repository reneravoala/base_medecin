<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Med</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://unpkg.com/@tailwindcss/forms@0.2.1/dist/forms.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="header.jsp" %>
<div class="w-full flex py-36">
    <a href="${pageContext.request.contextPath}/client/index" class="mx-auto inline-flex justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">Commencer</a>
</div>
</body>
</html>