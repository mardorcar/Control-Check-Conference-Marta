<%--
 * list.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:display code="quolet.ticker" path="${quolet.ticker}" />
<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
		<spring:message code="quolet.publicationMoment"/>: <fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:when>
    <jstl:otherwise>
    	<spring:message code="quolet.publicationMoment"/>: <fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
<acme:display code="quolet.cosa2" path="${quolet.cosa2}" />
<acme:display code="quolet.cosa3" path="${quolet.cosa3}" />



<br>
<acme:cancel url="/quolet/administrator/list.do"
							code="msg.cancel" />

