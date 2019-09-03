<%--
 * list.jsp
 *
 * Copyright (C) 2019 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<h2><spring:message code="quolet.finalQuolets"/> </h2>

<display:table pagesize="5" name="finalQuolets" id="quolet"
	requestURI="${requestURI}" class="displaytag table">
	<display:column titleKey="quolet.ticker"  property="ticker" />
	<display:column titleKey="quolet.publicationMoment"  property="publicationMoment" />
	<display:column titleKey="quolet.conference"  property="conference.title" />
	<display:column titleKey="quolet.mode"  property="mode" />
	<display:column titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
</display:table>
	
<h2><spring:message code="quolet.draftQuolets"/> </h2>

<display:table pagesize="5" name="draftQuolets" id="quolet"
	requestURI="${requestURI}" class="displaytag table">
	<display:column titleKey="quolet.ticker"  property="ticker" />
	<display:column titleKey="quolet.publicationMoment"  property="publicationMoment" />
	<display:column titleKey="quolet.conference"  property="conference.title" />
	<display:column titleKey="quolet.mode"  property="mode" />	
	<display:column titleKey="quolet.edit">
			<jstl:if test="${quolet.mode=='DRAFT'}">
	<acme:cancel url="/quolet/administrator/edit.do?quoletId=${quolet.id}" code="quolet.edit" />
		</jstl:if>
	</display:column>	
	<display:column titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	
</display:table>

<br>
<br>
	<acme:cancel url="/quolet/administrator/create.do"
		code="quolet.create" />



