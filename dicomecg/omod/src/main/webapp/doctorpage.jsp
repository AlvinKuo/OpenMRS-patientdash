<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<link href="${pageContext.request.contextPath}/moduleResources/dicomecg/tablestyle.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
function AddInterpretation(){
	
	/* document.doctorpage.comment.value += doucment.doctorpage.comment.value + ","; */
	document.commentform.comments.value += document.commentform.cardiology.value + ",";
	
}
</script>

<h2><spring:message code="dicomecg.doctor" /></h2>
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
						<c:forEach var="attri" items="${attribute}" varStatus="ind">
							<td><font color=#6600FF>${attri.gender}</font></td>																
						</c:forEach>
					</div>
					<div>
						<spring:message code="dicomecg.doctor.hight"/>
						<c:forEach var="attri" items="${attribute}" varStatus="ind">
							<td><font color=#6600FF>${attri.height}</font></td>																
						</c:forEach>
					</div>
					<div>
						<spring:message code="dicomecg.doctor.weight"/>
						<c:forEach var="attri" items="${attribute}" varStatus="ind">
							<td><font color=#6600FF>${attri.weight}</font></td>												
						</c:forEach>
					</div>
					<div>
						<spring:message code="dicomecg.doctor.heartrate"/>
						<c:forEach var="wave" items="${wave}" varStatus="ind">
							<td><font color=#6600FF>${wave.heartrate}</font></td>												
						</c:forEach>
					</div>
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
			<form method="POST" name="commentform">
				<select name="cardiology">
				  <option value="Atherosclerosis">Atherosclerosis</option>
				  <option value="Arteriosclerosis">Arteriosclerosis</option>
				  <option value="Angina Pectoris">Angina Pectoris</option>
				  <option value="Aneurysm">Aneurysm</option>
				</select>
					
				<input type="button" value="Add" onclick="AddInterpretation();"/>
					
				<br>
				
				<textarea class="comments" name="comments" cols="30" rows="20" 
		           style=" font-size:12px; background-color:#FEFF91;  border:double" >
	
		            
		        </textarea>		
				<br>
				<input	type="submit" value="Confirm" class="btn" />
				
			</form>
	</div>
	
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