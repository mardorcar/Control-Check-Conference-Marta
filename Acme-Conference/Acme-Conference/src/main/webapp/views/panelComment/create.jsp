<%--
 * index.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form
	action="panel/comment/create.do?panelId=${panelId}"
	modelAttribute="panelComment" method="post">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<acme:textbox code="panelComment.author" path="author" />
	<acme:textbox code="panelComment.title" path="title" />
	<acme:textarea code="panelComment.text" path="text" />

	<acme:submit name="save" code="panelComment.save" />
	<acme:cancel code="panelComment.cancel"
		url="conference/activity/listCommentPanel.do?panelId=${panelId}" />
</form:form>