<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first">
		<a href="${pageContext.request.contextPath}/admin">
		<spring:message	code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if>>
		<a href="${pageContext.request.contextPath}/module/dicomecg/manage.form">
		<spring:message	code="dicomecg.manage" /></a>
	</li>
	
	<li 
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if> >
		<a href="${pageContext.request.contextPath}/module/dicomecg/doctorpage.form">
		<spring:message	code="dicomecg.doctor" /></a>
	</li>

	<li 
		<c:if test='<%= request.getRequestURI().contains("/manage") %>'>class="active"</c:if> >
		<a href="${pageContext.request.contextPath}/module/dicomecg/idgenerator.form">
		<spring:message	code="dicomecg.idgen" /></a>
	</li>
	
	<!-- Add further links here -->
</ul>
