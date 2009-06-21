<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Add an Article</title>
	</head>
<body>
	<form:form>
		<p><form:label path="id">Id</form:label><br/>
		<form:input path="id"/></p>
		<p><form:label path="title">Title</form:label><br/>
		<form:input path="title"/></p>
		<p><form:label path="text">Text</form:label><br/>
		<form:textarea path="text" cols="50" rows="10"/></p>
		<p><input type="submit" /></p>
	</form:form>
</body>
</html>