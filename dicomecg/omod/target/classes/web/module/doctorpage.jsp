<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<link href="${pageContext.request.contextPath}/moduleResources/dicomecg/tablestyle.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">

function init(){
	
	document.commentform.comments.value += document.commentform.confirmComment.value;
	
}

function AddInterpretation(){
	
	document.commentform.comments.value += document.commentform.cardiology.value + ",";
	
}

window.onload = init;
 
</script>

<h2><spring:message code="dicomecg.doctor" /></h2>
<p>Hello ${user.username}!</p>


<!--  confirm has data -->

<div class="bigContainer" id="bigContainer">
	
	<!--Left show Patient Iformation -->
	<div class="pinformation" id="pinformation">
		
		<h3><spring:message code="dicomecg.doctor.information"/></h3>
			<div class="whiteBackground" id="whiteBackground">
				<h4>
					<c:forEach var="page" items="${doctorpage}" varStatus="ind">
					
						<div>
							<spring:message code="dicomecg.doctor.patient"/>
							<td><font color=#6600FF>${page.identifier}</font></td>
						</div>
						<div>
							<spring:message code="dicomecg.doctor.patientname"/>
							<td><font color=#6600FF>${page.patientName}</font></td>
						</div>
						<div>
							<spring:message code="dicomecg.doctor.measuretime"/>
							<td><font color=#6600FF>${page.measureTime}</font></td>
						</div>
					</c:forEach>
					
					<c:forEach var="attri" items="${attribute}" varStatus="ind">
					
						<div>
							<spring:message code="dicomecg.doctor.gender"/>
							<td><font color=#6600FF>${attri.gender}</font></td>
						</div>
						<div>
							<spring:message code="dicomecg.doctor.height"/>
							<td><font color=#6600FF>${attri.height}</font></td>
						</div>
						<div>
							<spring:message code="dicomecg.doctor.weight"/>
							<td><font color=#6600FF>${attri.weight}</font></td>
						</div>
						
					</c:forEach>
					
					<c:forEach var="wave" items="${wave}" varStatus="ind">
						<div>
							<spring:message code="dicomecg.doctor.heartrate"/>
							<td><font color=#6600FF>${wave.heartrate}</font></td>
						</div>
						<div>
							<spring:message code="dicomecg.doctor.ecg.rr"/>
							<td><font color=#6600FF>${wave.comment}</font></td>
						</div>
						<div>
							<spring:message code="dicomecg.doctor.filename"/>
							<td><a target="_new" href="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=${wave.filename}">${wave.filename}</a></td>
						</div>					
					</c:forEach>					
				</h4>
			</div>
			
			<!-- show Comment area -->
			
				<form method="POST" target="_new" name="commentform" action="${pageContext.request.contextPath}/moduleServlet/dicomecg/SaveInterpretation">
					<h3><spring:message code="dicomecg.doctor.interpretation"/></h3>
					<div class="whiteBackground" id="whiteBackground">	
					
						<c:forEach var="confirm" items="${confirm}">
							<input type="hidden" name="confirmId" value="${confirm.id}">							
							<input type="hidden" name="confirmComment" value="${confirm.comment}">
							<!-- <input type="button" name="confirmComment" value="Add old confirm" onclick="init();"/> -->
						</c:forEach>
						<openmrs:hasPrivilege privilege="Edit Physician Page">
						<select name="cardiology" size="5" autofocus onclick="AddInterpretation();">
							<option value="Anemia">Anemia</option>
						  	<option value="Aneurysm">Aneurysm</option>
						  	<option value="Angina">Angina</option>
						  	<option value="Angioplasty">Angioplasty</option>
						  	<option value="Arrhythmia">Arrhythmia</option>
						  	<option value="Arteriosclerosis">Arteriosclerosis</option>
						  	<option value="Artery">Artery</option>
						  	<option value="Atrium">Atrium</option>
						  	<option value="Beating extremely fast">Beating extremely fast</option>
						  	<option value="Black out">Black out</option>
						  	<option value="Blood vessel">Blood vessel</option>
						  	<option value="Bronchitis">Bronchitis</option>
						  	<option value="Burning feeling in chest">Burning feeling in chest</option>
						  	<option value="Cardiac arrest">Cardiac arrest</option>
						  	<option value="Cardiac asthma">Cardiac asthma</option>
						  	<option value="Chest pain">Chest pain</option>
						  	<option value="Chest tight">Chest tight</option>
						  	<option value="Congenital heart disease">Congenital heart disease</option>
						  	<option value="Cor pulmonale">Cor pulmonale</option>
						  	<option value="Coronary artery by-pass operation">Coronary artery by-pass operation</option>
						  	<option value="Coronary occlusion">Coronary occlusion</option>
						  	<option value="Cyanosis">Cyanosis</option>
						  	<option value="Dizzy">Dizzy</option>
						  	<option value="Edema">Edema</option>
						  	<option value="Endocarditis">Endocarditis</option>
						  	<option value="Fainted">Fainted</option>
						  	<option value="Heart">Heart</option>
						  	<option value="Heart attack">Heart attack</option>
						  	<option value="Heart block">Heart block</option>
						  	<option value="Heart complication">Heart complication</option>
						  	<option value="Heart failure">Heart failure</option>
						  	<option value="Heart murmur">Heart murmur</option>
						  	<option value="Hypotension">Hypotension</option>
						  	<option value="Irregular heartbeat">Irregular heartbeat</option>
						  	<option value="Lymph node">Lymph node</option>
						  	<option value="Mitral stenosis">Mitral stenosis</option>
						  	<option value="Myocardial infarction">Myocardial infarction</option>
						  	<option value="Myocarditis">Myocarditis</option>
						  	<option value="Pale face">Pale face</option>
						  	<option value="Palpitation">Palpitation</option>
						  	<option value="Paroxysmal tachycardia">Paroxysmal tachycardia</option>
						  	<option value="Pericarditis ">Pericarditis </option>
						  	<option value="Rheumatic heart disease">Rheumatic heart disease</option>
						  	<option value="Scurvy">Scurvy</option>						  	
						  	<option value="Short of breath">Short of breath</option>
						  	<option value="Sudden stop in my heartbeat">Sudden stop in my heartbeat</option>
						  	<option value="Thrombosis">Thrombosis</option>
						  	<option value="Tracheitis">Tracheitis</option>
						  	<option value="Valve replacement">Valve replacement</option>
						  	<option value="Valvular cyanosis">Valvular cyanosis</option>
						  	<option value="Valvular incompetence">Valvular incompetence</option>
						  	<option value="Valvular stenosis">Valvular stenosis</option>
						  	<option value="Vein">Vein</option>
						  	<option value="Ventricle">Ventricle</option>
						</select>
						</openmrs:hasPrivilege>
						
						<!-- <input type="button" value="Add" onclick="AddInterpretation();"/> -->
							
						<br>
						<c:forEach var="page" items="${doctorpage}" varStatus="ind">
							<input type="hidden" name="ecgId" value="${page.id}"/>
							<input type="hidden" name="patientId" value="${page.patiendId}"/>							
							<input type="hidden" name="identifier" value="${page.identifier}"/>
							<input type="hidden" name="patientName" value="${page.patientName}"/>
							<input type="hidden" name="nurseId" value="${page.nurseId}"/>
							<input type="hidden" name="nurseName" value="${page.nurseName}"/>
							<input type="hidden" name="measureTime" value="${page.measureTime}"/>
							<input type="hidden" name="uploadTime" value="${page.uploadTime}"/>
							<input type="hidden" name="filename" value="${page.filename}"/>
							<input type="hidden" name="confirmName" value="${user.username}"/>
						</c:forEach>
						<textarea id="comments" class="comments" name="comments" cols="30" rows="20" style=" font-size:14px; 
							background-color:#FEFF91;  border:double" ></textarea>		
						<br>

						<openmrs:hasPrivilege privilege="Edit Physician Page">
							<input type="radio" name="feedback" value="Steady" checked /> <spring:message code="dicomecg.patient.steady"/> 
							<input type="radio" name="feedback" value="Unstable"/> <spring:message code="dicomecg.patient.unstable"/> <br>
							<input	type="submit" value="Confirm" class="btn" /><br>					
						</openmrs:hasPrivilege>
								

					</div>
				</form>			
	</div>
	
	<div class="ecgview" id="ecgview">	   
       <!--  show in image -->
       	<c:forEach var="page" items="${doctorpage}" varStatus="ind">
			<img height=100% width=100% src="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=${page.filename}" alt="Here is show 12 lead ecg picture" ismap />
       	</c:forEach> 
	</div>
	
</div>

<div style="clear: both"></div>
<%@ include file="/WEB-INF/template/footer.jsp"%>