<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<div class="box">

	<form method="POST" name="DicomEcgTable">
	
<%-- 	<form id="uploadDicom" method="POST" 
	action="${pageContext.request.contextPath}/moduleServlet/dicomecg/DicomUpload" 
	enctype="multipart/form-data">	 --%>
	
	<table>
		
		<tr>
			<td><spring:message code="dicomecg.ecg.patiendId"/></td>
			<td><input type="text" name="patiendId" value="${patiendId}" /></td>			
		</tr> 	
		<tr>
			<td><spring:message code="dicomecg.ecg.patientName"/></td>
			<td><input type="text" name="patientName" value="${patientName}" /></td>			
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.nurseId"/></td>
			<td><input type="text" name="nurseId" value="${nurseId}" /></td>			
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.nurseName"/></td>
			<td><input type="text" name="nurseName" value="${nurseName}" /></td>			
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.filename"/></td>
			<td><input type="text" name="filename" value="${filename}" /></td>			
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.measureTime"/></td>
			<td><input type="text" name="measureTime" value="${measureTime}" /></td>			
		</tr> 			
		<tr>
			<td><spring:message code="dicomecg.ecg.uploadTime"/></td>
			<td><input type="text" name="uploadTime" value="${uploadTime}" /></td>			
		</tr> 	
		<tr>
			<td></td>
			<td><input type="submit" value="Add New" /></td>
		</tr>	
	</table>
	</form>	
	
	<form id="uploadDicom" method="POST" 
	action="${pageContext.request.contextPath}/moduleServlet/dicomecg/DicomUpload" 
	enctype="multipart/form-data">
		<input type = "submit" value = "Upload" > 	
	</form>
	

	<table id="dicomEcg" border="1">
		<tr bgcolor="#00FFFF" bordercolor="#000000">
			<th align="center">ID</th>
			<th align="center">Patient ID</th>
			<th align="center">Patient Name</th>		
			<th align="center">Nurse ID</th>
			<th align="center">Nurse Name</th>
			<th align="center">File</th>
			<th align="center">Measure Time</th>
			<th align="center">Upload Time</th>
			<th align="center">Action</th>
		</tr>
		
		<c:forEach var="ecg" items="${dicomecg}" varStatus="ind">
			<form method="POST" name="${DicomEcg.id}" >
				<tr>
					<td>${ind.index + 1 }</td>
					<td>${ecg.patiendId }</td>
					<td>${ecg.patientName }</td>
					<td>${ecg.nurseId }</td>
					<td>${ecg.nurseName }</td>
					<td>${ecg.filename }</td>
					<td>${ecg.measureTime }</td>
					<td>${ecg.uploadTime }</td>							
				</tr>						
			</form>		
		</c:forEach>
		
			
	</table>
	
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>