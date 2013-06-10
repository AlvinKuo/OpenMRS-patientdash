<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="dicomecg.title" />
</h2>


<p>Hello ${user.systemId}!</p>

<div class="box">

 	<!-- <form method="POST" name="DicomEcgTable"> -->
	
 	<form id="uploadDicom" method="POST" 
	action="${pageContext.request.contextPath}/moduleServlet/dicomecg/DicomUpload">	 
	
	<table>
		
		<tr>
			<td><spring:message code="dicomecg.ecg.identifier"/></td>
			<%-- <td><input type="text" name="patiendId" value="${patiendId}" /></td> --%>
			<td><input type="text" name="patient_id" id="patiendId" /></td>
		</tr> 	
		<tr>
			<td><spring:message code="dicomecg.ecg.patientName"/></td>
			<td><input type="text" name="patient_name" id="patientName" /></td>						
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.nurseId"/></td>
			<td><input type="text" name="nurse_id" id="nurseId" /></td>			
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.nurseName"/></td>
			<td><input type="text" name="nurse_name" id="nurseName" /></td>
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.filename"/></td>
			<td><input type="text" name="filename" id="filename" /></td>
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.measureTime"/></td>
			<td><input type="text" name="measure_time" id="measureTime" /></td>			
		</tr> 			
		<tr>
			<td></td>
			<td><input type="submit" value="Add New" /></td>
		</tr>	
	</table>
	</form>	 
	
<%--  	<form id="uploadDicom" method="POST" 
	action="${pageContext.request.contextPath}/moduleServlet/dicomecg/DicomUpload" 
	enctype="multipart/form-data">
		<input type = "submit" value = "Upload" > 	
	</form>  --%>
	

	<table id="dicomEcg" border="1">
		<tr bgcolor="#00FFFF" bordercolor="#000000">
			<th align="center">ID</th>
			<th align="center">Patient Identifier</th>
			<th align="center">Patient Name</th>		
			<th align="center">Nurse ID</th>
			<th align="center">Nurse Name</th>
			<th align="center">File</th>
			<th align="center">Measure Time</th>
			<th align="center">Upload Time</th>
			<th align="center">Action</th>
		</tr>
		
		<c:forEach var="ecg" items="${dicomecg}" varStatus="ind">
			<form method="POST" name="${ecg.id}" >
				<tr>
					<td>${ind.index + 1 }</td>
					<td>${ecg.patiendId }</td>
					<td>${ecg.patientName }</td>
					<td>${ecg.nurseId }</td>
					<td>${ecg.nurseName }</td>
					<td>${ecg.filename }</td>
					<td>${ecg.measureTime }</td>
					<td>${ecg.uploadTime }</td>
					<td><a target="_new" href="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=${ecg.filename }">Patient ECG</a></td>					
											
				</tr>						
			</form>		
		</c:forEach>			
	</table>
	
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>