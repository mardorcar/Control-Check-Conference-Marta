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

<div class="container">
	<div class="row">
		<div class="col-sm-12 col-md-12 col-lg-12">
			<fieldset class="col-md-6 col-md-offset-3">

				<form:form action="oblemic/administrator/edit.do"
					modelAttribute="oblemic" class="form-horizontal" method="post">
					<div class="form-group ">

						<form:hidden path="id"/>
						<form:hidden path="version"/>	
						<acme:textarea code="oblemic.body" path="body" />
						<acme:textbox code="oblemic.picture" path="picture" />
				
		
						<acme:select items="${conferences}" itemLabel="title" code="oblemic.conferences" path="conference"/>
					
						<spring:message code="oblemic.mode"/>
						<form:select path="mode">
							<form:option value="DRAFT"/>
							<form:option value="FINAL" />
						</form:select>
						
						<acme:submit name="save" code="oblemic.msg.save" />						
						<acme:cancel url="/oblemic/administrator/list.do"
							code="msg.cancel" />
							
						 <jstl:if test="${oblemic.id!=0}">
							<acme:submit name="delete" code="msg.delete" />
						</jstl:if> 
				
					</div>
				</form:form>
			</fieldset>
		

		</div>
	</div>
</div>
