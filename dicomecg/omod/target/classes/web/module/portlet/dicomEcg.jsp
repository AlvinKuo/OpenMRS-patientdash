<%@ include file="/WEB-INF/template/include.jsp" %>


<!-- Give a table format to show the data  -->

<b class="boxHeader"><spring:message code="dicomecg.ecgdatalist"/></b>
	<div class="box" align="center" >
	
		<table id="dicomEcg" border="1"  cellpadding="5" width="80%">
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
			<tbody>
			
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
<%-- 					
					<td>
						<input type="hidden" name="houseid" id="${household.id}" value="${household.id}" />
						<input type="submit" value="Edit" />
						<!-- <a href="#">Edit</a> -->
					</td>
					<img alt="" id="patientimg" height="300" width="300" src="${pageContext.request.contextPath}/moduleServlet/patientimage/ImageServlet?image=${model.patient_image}" />
--%>					
											
						</tr>						
					</form>		
				</c:forEach>
			</tbody>			
		</table>
					
	</div>
	
