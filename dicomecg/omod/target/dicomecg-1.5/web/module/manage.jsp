<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<p>Hello ${user.systemId}!</p>

<div class="box">
	<table id="dicomEcg" border="1">
		<tr bgcolor="#00FFFF" bordercolor="#000000">
			<th align="center">Patient ID</th>
			<th align="center">Patient Name</th>		
			<th align="center">Nurse ID</th>
			<th align="center">Nurse Name</th>
			<th align="center">File</th>
			<th align="center">Measure Time</th>
			<th align="center">Upload Time</th>
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