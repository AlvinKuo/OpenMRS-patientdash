<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>



<script type="text/javascript">

function AddInterpretation(){
	
	document.doctorpage.comment.value += doucment.doctorpage.comment.value + ",";

	return false;
}

</script>

<h2>
	<spring:message code="dicomecg.doctor" />
</h2>

<link href="${pageContext.request.contextPath}/moduleResources/dicomecg/tablestyle.css" type="text/css" rel="stylesheet" />
<p>Hello ${user.systemId}!</p>


<div class="bigContainer" id="bigContainer">
	
	
	<!--Left show Patient Iformation -->
	
	<div class="pinformation" id="pinformation">
		
		<h3><spring:message code="dicomecg.doctor.information"/></h3>
			<div class="whiteBackground" id="whiteBackground">	
				<h4>		
					<div>
						<spring:message code="dicomecg.doctor.patient"/>					
						<c:forEach var="page" items="${doctorpage}" varStatus="ind">
							<td><font color=#6600FF>${page.identifier}</font></td>																
						</c:forEach>												
					</div>
					<div>
						<spring:message code="dicomecg.doctor.patientname"/>
						<c:forEach var="page" items="${doctorpage}" varStatus="ind">
							<td><font color=#6600FF>${page.patientName}</font></td>																
						</c:forEach>
					</div>					
					<div>
						<spring:message code="dicomecg.doctor.gender"/>
					</div>
					<div><spring:message code="dicomecg.doctor.hight"/></div>
					<div><spring:message code="dicomecg.doctor.weight"/></div>
					<div><spring:message code="dicomecg.doctor.bloodpressure"/></div>
					<div>
						<spring:message code="dicomecg.doctor.filename"/>
						<c:forEach var="page" items="${doctorpage}" varStatus="ind">
							<td><font color=#6600FF>${page.filename}</font></td>																
						</c:forEach>	
					</div>
					<div>
						<spring:message code="dicomecg.doctor.measuretime"/>
						<c:forEach var="page" items="${doctorpage}" varStatus="ind">
							<td><font color=#6600FF>${page.measureTime}</font></td>																
						</c:forEach>
					</div>
				</h4>
			</div>
		
		<!-- show Comment area -->
		<h3><spring:message code="dicomecg.doctor.interpretation"/></h3>
			<div class="whiteBackground" id="whiteBackground">
			
			<form method="POST" name="doctorpage">
			
			Choose a Concept:<openmrs_tag:conceptField formFieldName="conceptId" ></openmrs_tag:conceptField>
			<input type="submit" value="View"/>
			
			<table>
			 	<tr>
			    	<th>Name</th>    
			  	</tr>
			  				
				<c:forEach var="cn" items="${ concept.names}">
					<tr>
						<td>
							<input name="conceptName" type="text" value="${cn.name}">
						</td>
						<td>
							<input type="button" value="Add" onclick="AddInterpretation()"/>
						</td>
					</tr>				
				</c:forEach>

			</table>
			
			<textarea class="comment" name="comment" cols="30" rows="20" 
            style=" font-size:12px; background-color:#FEFF91;  border:double" ></textarea>		
				<br>
				<input	type="submit" value="Confirm" class="btn" />
				<input type=button value="Refresh" onClick="window.location.reload()">
				</form>
			</div>
			
	</div>

	<!--  TextViewer show ecg table -->
	<div class="ecgview" id="ecgview">
	   
	   <p>Here is show 12 lead ecg picture</p>
       <!--  show in image -->
       	<c:forEach var="page" items="${doctorpage}" varStatus="ind">
			<img height=100% width=100% src="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=${page.filename}" alt="Here is show 12 lead ecg picture" ismap />
       	</c:forEach> 
	</div>
	
	
</div>

<div style="clear: both"></div>
<%@ include file="/WEB-INF/template/footer.jsp"%>