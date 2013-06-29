<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="dicomecg.title" />
</h2>


<p>Hello ${user.systemId}!</p>

<div class="box">

 	<!-- <form method="POST" name="DicomEcgTable"> -->
	<div align="left" width="50%">
 		<form id="uploadDicom" method="POST" target="_new"
			action="${pageContext.request.contextPath}/moduleServlet/dicomecg/DicomUpload">
			<table>		
				<tr>
					<td><spring:message code="dicomecg.ecg.identifier"/></td>
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
					<td><input type="submit" value="Add New" /></td>
				</tr>	
			</table>
		</form>	
	</div>
<!--	
	<div align="cneter" width="50%">
		<form id="uploadAttribute" method="POST" target="_new"
		action="${pageContext.request.contextPath}/moduleServlet/dicomecg/AttributeTest">
 			<table>
				<tr>
					<td></td>
					<td><input type="text" name="identi" id="identi" /></td>
				</tr>			
				<tr>
					<td><spring:message code="dicomecg.ecg.patiendId"/></td>
					<td><input type="text" name="patiendId" id="patiendId" /></td>
				</tr>		
				<tr>
					<td><spring:message code="dicomecg.doctor.gender"/></td>
					<td><input type="text" name="gender" id="gender" /></td>
				</tr>
				<tr>
					<td><spring:message code="dicomecg.doctor.hight"/></td>
					<td><input type="text" name="height" id="height" /></td>
				</tr>
				<tr>
					<td><spring:message code="dicomecg.doctor.weight"/></td>
					<td><input type="text" name="weight" id="weight" /></td>
				</tr>
				<tr>
					<td><spring:message code="dicomecg.doctor.birthdate"/></td>
					<td><input type="text" name="birthdate" id="birthdate" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Add Attribute" /></td>
				</tr>
			</table> -->
	<div align="cneter" width="50%">
			<form id="uploadAttribute" method="POST" target="_new"
			action="${pageContext.request.contextPath}/moduleServlet/dicomecg/HeartRateCount">
			<tr>
				<td><p>Filename</p></td>
				<td><input type="text" name="filename" id="filename" /></td>
			</tr>			
			<tr>
				<td><input type="submit" value="Test Read Dicom Filename" /></td>
			</tr>			
		</form>	
	</div>
	
	<div align="left" width="50%">
		<table id="dicomEcg" border="1">
			<tr bgcolor="#00FFFF" bordercolor="#000000">
				<th align="center">Ecg ID</th>
				<th align="center">OpenMRS ID</th>
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
				<form method="POST" name="${ecg.id}" target="_new" >
					<tr>
						<td>${ecg.id}</td>
						<td>${ecg.patiendId}</td>
						<td>${ecg.identifier }</td>
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
	
	<div align="letf" width="50%">
		<table id="dicomAttribute" border="1">
			<tr bgcolor="#00FFFF" bordercolor="#000000">
				<th align="center">Ecg Attribute ID</th>
				<th align="center">Patient ID</th>
				<th align="center">Gender</th>
				<th align="center">Height</th>
				<th align="center">Weight</th>
				<th align="center">Birth Date</th>				
				<th align="center">Filename</th>			
			</tr>

			<c:forEach var="attribute" items="${attribute}" varStatus="ind">
				<form method="POST" name="${attribute.id}" >
					<tr>						
						<td>${attribute.id }</td>
						<td>${attribute.patiendId }</td>
						<td>${attribute.gender }</td>
						<td>${attribute.height }</td>
						<td>${attribute.weight }</td>
						<td>${attribute.birthdate }</td>
						<td>${attribute.filename }</td>				
					</tr>						
				</form>		
			</c:forEach>

		</table>
	</div>
	
	<div align="letf" width="50%">
		<table id="dicomConfirm" border="1">
			<tr bgcolor="#00FFFF" bordercolor="#000000">
				<th align="center">Ecg Confirm ID</th>
				<th align="center">Patient Time</th>
				<th align="center">Confirm Name</th>		
				<th align="center">Confirm</th>
				<th align="center">Comment</th>
				<th align="center">Filename</th>
				<th align="center">Mail Address</th>			
			</tr>

			<c:forEach var="confirm" items="${confirm}" varStatus="ind">
				<form method="POST" name="${confirm.id}" >
					<tr>						
						<td>${confirm.id }</td>
						<td>${confirm.patientId }</td>
						<td>${confirm.identifier }</td>
						<td>${confirm.confirmTime }</td>
						<td>${confirm.confirmName }</td>
						<td>${confirm.comment }</td>
						<td>${confirm.filename }</td>
						<td>${confirm.mial }</td>				
					</tr>						
				</form>		
			</c:forEach>

		</table>
	</div>
	
	
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>