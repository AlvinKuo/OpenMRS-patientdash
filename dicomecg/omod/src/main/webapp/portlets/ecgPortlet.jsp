<%@ include file="/WEB-INF/template/include.jsp"%>


<b class="boxHeader"><spring:message code="dicomecg.ecgdatalist" /></b>
<div class = "box" align="center">

<table id="dicomEcg" border="1" cellpadding="5" width="80%">

	<tr bgcolor="#00FFFF" bordercolor="#000000">
		<th align="center">ID</th>
		<th align="center">Patient Name</th> 
		<th align="center">File</th>
		<th align="center">Measure Time</th>
		<th align="center">Upload Time</th>
		<th align="center">Confirm</th>
		<th align="center">Action</th>			
	</tr>


	<c:forEach var="ecglist" items="${model.portecg}" varStatus="ind">
	<tr>
		<td class="tdClass"><c:out value="${ind.index}"/></td>
		<td class="tdClass"><c:out value="${ecglist.patientName}"/></td>
		<td class="tdClass"><c:out value="${ecglist.filename}"/></td>
		<td class="tdClass"><c:out value="${ecglist.measureTime}"/></td>
		<td class="tdClass"><c:out value="${ecglist.uploadTime}"/></td>
		<td class="tdClass"><c:out value="${ecglist.uploadTime}"/></td>
		<td><a target="_new" href="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=${ecglist.filename }">Patient ECG</a></td>
	<tr>	
	</c:forEach>
	
	
	<!-- test mapping to right patient  -->
	<td class="tdClass"><c:out value="${model.personx}"/></td>
	
	
	
	

</table>
</div>