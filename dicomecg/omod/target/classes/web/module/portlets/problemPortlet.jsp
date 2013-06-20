<%@ include file="/WEB-INF/template/include.jsp" %>

<%-- <p>Hello ${user.systemId}!</p> --%>

<form method="POST" action="listproblem.form">
Choose a Concept: <openmrs_tag:conceptField formFieldName="newConceptId" ></openmrs_tag:conceptField>

<input type="submit" value="View"/>

</form>

<%-- <c:out escapeXml="true" value="${newConcept.name}"/> --%>