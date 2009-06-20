<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title><c:out value="${article.title}" /></title>
</head>
<body>
	<h1><c:out value="${article.title}" /></h1>
	<h3>Published <fmt:formatDate value="${article.created}" type="both" dateStyle="short" timeStyle="short" /></h3>
	<h3>Permalink: <c:url value="${article.id}" /></h3>
	<div>
		<c:out value="${article.text.value}" />
	</div>
</body>
</html>