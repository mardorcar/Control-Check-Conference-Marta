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
	
	<jstl:if test="${((date-quolet.publicationMoment.time)/86400000)<30}">
	<display:column style="color:indigo" titleKey="quolet.ticker"  property="ticker" />

	<display:column style="color:indigo" titleKey="quolet.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:indigo" titleKey="quolet.conference"  property="conference.title" />
	<display:column style="color:indigo" titleKey="quolet.mode"  property="mode" />
	<display:column style="color:indigo" titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	
	</jstl:if>
			<jstl:if test="${((date-remark.moment.time)/86400000)>30}">
			<jstl:if test="${((date-remark.moment.time)/86400000)<60}">
	<display:column style="color:darkSlateGrey" titleKey="quolet.ticker"  property="ticker" />

	<display:column style="color:darkSlateGrey" titleKey="quolet.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:darkSlateGrey" titleKey="quolet.conference"  property="conference.title" />
	<display:column style="color:darkSlateGrey" titleKey="quolet.mode"  property="mode" />
	<display:column style="color:darkSlateGrey" titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	
	</jstl:if>
			</jstl:if>
			<jstl:if test="${((date-remark.moment.time)/86400000)>60}">
		<display:column style="color:papayaWhip" titleKey="quolet.ticker"  property="ticker" />

	<display:column style="color:papayaWhip" titleKey="quolet.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:papayaWhip" titleKey="quolet.conference"  property="conference.title" />
	<display:column style="color:papayaWhip" titleKey="quolet.mode"  property="mode" />
	<display:column style="color:papayaWhip" titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	</jstl:if>	
	
</display:table>
	
<h2><spring:message code="quolet.draftQuolets"/> </h2>

<display:table pagesize="5" name="draftQuolets" id="quolet"
	requestURI="${requestURI}" class="displaytag table">
	<jstl:if test="${((date-quolet.publicationMoment.time)/86400000)<30}">
	<display:column style="color:indigo" titleKey="quolet.ticker"  property="ticker" />

	<display:column style="color:indigo" titleKey="quolet.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:indigo" titleKey="quolet.conference"  property="conference.title" />
	<display:column style="color:indigo" titleKey="quolet.mode"  property="mode" />
	<display:column style="color:indigo" titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	
	</jstl:if>
			<jstl:if test="${((date-remark.moment.time)/86400000)>30}">
			<jstl:if test="${((date-remark.moment.time)/86400000)<60}">
	<display:column style="color:darkSlateGrey" titleKey="quolet.ticker"  property="ticker" />

	<display:column style="color:darkSlateGrey" titleKey="quolet.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:darkSlateGrey" titleKey="quolet.conference"  property="conference.title" />
	<display:column style="color:darkSlateGrey" titleKey="quolet.mode"  property="mode" />
	<display:column style="color:darkSlateGrey" titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	
	</jstl:if>
			</jstl:if>
			<jstl:if test="${((date-remark.moment.time)/86400000)>60}">
		<display:column style="color:papayaWhip" titleKey="quolet.ticker"  property="ticker" />

	<display:column style="color:papayaWhip" titleKey="quolet.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${quolet.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${quolet.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:papayaWhip" titleKey="quolet.conference"  property="conference.title" />
	<display:column style="color:papayaWhip" titleKey="quolet.mode"  property="mode" />
	<display:column style="color:papayaWhip" titleKey="quolet.show">
		<acme:cancel url="/quolet/administrator/show.do?quoletId=${quolet.id}" code="quolet.show" />
	</display:column>
	</jstl:if>	
	
</display:table>

<br>
<br>
	<acme:cancel url="/quolet/administrator/create.do"
		code="quolet.create" />



