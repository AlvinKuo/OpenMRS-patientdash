<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<link href="${pageContext.request.contextPath}/moduleResources/dicomecg/tablestyle.css" type="text/css" rel="stylesheet" />

<p>Hello ${user.systemId}!</p>



<div class="bigContainer" id="bigContainer">

	<!--Left show Patient Iformation -->
	<div class="pinformation" id="pinformation">
		<h3><spring:message code="dicomecg.doctor.information"/></h3>
			<div class="whiteBackground" id="whiteBackground">	
				<h4>		
					<spring:message code="dicomecg.doctor.patient"/>
					<div><spring:message code="dicomecg.doctor.patientname"/></div>
					<div><spring:message code="dicomecg.doctor.gender"/></div>
					<div><spring:message code="dicomecg.doctor.hight"/></div>
					<div><spring:message code="dicomecg.doctor.weight"/></div>
					<div><spring:message code="dicomecg.doctor.bloodpressure"/></div>
					<div><spring:message code="dicomecg.doctor.filename"/></div>
					<div><spring:message code="dicomecg.doctor.measuretime"/></div>			
				</h4>
		</div>
		
		<!-- show Comment area -->
		<form id="" class="" method="post" action="" >
			<h3><spring:message code="dicomecg.doctor.interpretation"/></h3>
				
				<div class="whiteBackground" id="whiteBackground">
				<br>
					<textarea class="comment" name="comments" cols="30" rows="20" 
             			  style=" font-size:12px; background-color:#FEFF91;  border:double" ></textarea>		
				<br>
				<input	type="submit" value="Confirm" class="btn" />
						
				</div>
			
		</form>
		
	</div>

	<!--  TextViewer show ecg table -->
	<div class="ecgview" id="ecgview">
	   
	   <p>Here is show 12 lead ecg picture</p>
<%-- 	   <object  codetype="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=B12345678920130527211933.dcm">
       </object>  --%>
       
       
       
       <!--  show in image -->
       <img height=100% width=100% src="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=B12345678920130527211933.dcm" alt="Here is show 12 lead ecg picture" ismap />
	</div>
	
	
</div>






<div style="clear: both;"></div>
<%@ include file="/WEB-INF/template/footer.jsp"%>