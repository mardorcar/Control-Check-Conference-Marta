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




<h2><spring:message code="oblemic.finalOblemicsAdmin"/> </h2>

<display:table pagesize="5" name="finalOblemicsAdmin" id="oblemic"
	requestURI="${requestURI}" class="displaytag table">
	
	<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<30}">
	<display:column style="color:red" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:red" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:red" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:red" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:red" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	
	</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>30}">
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<60}">
	<display:column style="color:blue" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:blue" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:blue" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:blue" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:blue" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	
	</jstl:if>
			</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>60}">
		<display:column style="color:green" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:green" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:green" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:green" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:green" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	</jstl:if>	
	
</display:table>

<h2><spring:message code="oblemic.draftOblemicsAdmin"/> </h2>

<display:table pagesize="5" name="draftOblemicsAdmin" id="oblemic"
	requestURI="${requestURI}" class="displaytag table">
	<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<30}">
	<display:column style="color:red" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:red" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:red" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:red" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:red" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	<display:column style="color:red" titleKey="oblemic.edit">
		<acme:cancel url="/oblemic/administrator/edit.do?oblemicId=${oblemic.id}" code="oblemic.edit" />
	</display:column>
	
	</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>30}">
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<60}">
	<display:column style="color:blue" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:blue" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:blue" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:blue" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:blue" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	<display:column style="color:blue" titleKey="oblemic.edit">
		<acme:cancel url="/oblemic/administrator/edit.do?oblemicId=${oblemic.id}" code="oblemic.edit" />
	</display:column>
	
	</jstl:if>
			</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>60}">
		<display:column style="color:green" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:green" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:green" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:green" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:green" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	<display:column style="color:green" titleKey="oblemic.edit">
		<acme:cancel url="/oblemic/administrator/edit.do?oblemicId=${oblemic.id}" code="oblemic.edit" />
	</display:column>
	</jstl:if>	
	
</display:table>


<h2><spring:message code="oblemic.finalOblemics"/> </h2>

<display:table pagesize="5" name="finalOblemics" id="oblemic"
	requestURI="${requestURI}" class="displaytag table">
	
	<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<30}">
	<display:column style="color:red" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:red" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:red" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:red" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:red" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	
	</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>30}">
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<60}">
	<display:column style="color:blue" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:blue" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:blue" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:blue" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:blue" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	
	</jstl:if>
			</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>60}">
		<display:column style="color:green" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:green" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:green" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:green" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:green" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	</jstl:if>	
	
</display:table>
	
<h2><spring:message code="oblemic.draftOblemics"/> </h2>

<display:table pagesize="5" name="draftOblemics" id="oblemic"
	requestURI="${requestURI}" class="displaytag table">
	<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<30}">
	<display:column style="color:red" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:red" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:red" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:red" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:red" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	<display:column style="color:red" titleKey="oblemic.edit">
		<acme:cancel url="/oblemic/administrator/edit.do?oblemicId=${oblemic.id}" code="oblemic.edit" />
	</display:column>
	
	</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>30}">
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)<60}">
	<display:column style="color:blue" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:blue" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:blue" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:blue" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:blue" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	<display:column style="color:blue" titleKey="oblemic.edit">
		<acme:cancel url="/oblemic/administrator/edit.do?oblemicId=${oblemic.id}" code="oblemic.edit" />
	</display:column>
	
	</jstl:if>
			</jstl:if>
			<jstl:if test="${((date-oblemic.publicationMoment.time)/86400000)>60}">
		<display:column style="color:green" titleKey="oblemic.ticker"  property="ticker" />

	<display:column style="color:green" titleKey="oblemic.publicationMoment">
	<jstl:choose>
	<jstl:when test="${lang eq 'en'}">
	<fmt:formatDate type = "date" pattern = "yy/MM/dd HH:mm"
         value = "${oblemic.publicationMoment}" />
     </jstl:when>
    <jstl:otherwise>
    	<fmt:formatDate type = "date" pattern = "dd-MM-yy HH:mm"
         value = "${oblemic.publicationMoment}" />
    </jstl:otherwise>
</jstl:choose>
</display:column>	

	<display:column style="color:green" titleKey="oblemic.conference"  property="conference.title" />
	<display:column style="color:green" titleKey="oblemic.mode"  property="mode" />
	<display:column style="color:green" titleKey="oblemic.show">
		<acme:cancel url="/oblemic/administrator/show.do?oblemicId=${oblemic.id}" code="oblemic.show" />
	</display:column>
	<display:column style="color:green" titleKey="oblemic.edit">
		<acme:cancel url="/oblemic/administrator/edit.do?oblemicId=${oblemic.id}" code="oblemic.edit" />
	</display:column>
	</jstl:if>	
	
</display:table>


<br>
<br>
	<acme:cancel url="/oblemic/administrator/create.do"
		code="oblemic.create" />



