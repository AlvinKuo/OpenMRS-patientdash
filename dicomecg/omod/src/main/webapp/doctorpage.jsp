<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

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
					<div><spring:message code="dicomecg.doctor.patient"/></div>
					<div><spring:message code="dicomecg.doctor.patientname"/></div>
					<div><spring:message code="dicomecg.doctor.gender"/></div>
					<div><spring:message code="dicomecg.doctor.hight"/></div>
					<div><spring:message code="dicomecg.doctor.weight"/></div>
					<div><spring:message code="dicomecg.doctor.bloodpressure"/></div>
					<div><spring:message code="dicomecg.doctor.filename"/></div>
					<div><spring:message code="dicomecg.doctor.measuretime"/></div>
					
					<!-- ecg attribute -->
					<div><spring:message code="dicomecg.doctor.ecg.rr"/></div>
					<div><spring:message code="dicomecg.doctor.ecg.rp"/></div>
					<div><spring:message code="dicomecg.doctor.ecg.qrsd"/></div>
					<div><spring:message code="dicomecg.doctor.ecg.qt"/></div>
					<div><spring:message code="dicomecg.doctor.ecg.p"/></div>
					<div><spring:message code="dicomecg.doctor.ecg.qrs"/></div>
					<div><spring:message code="dicomecg.doctor.ecg.t"/></div>
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
<%-- 	   <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="TestViewer" width="100%" height="100%"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			
			<param name="movie"	value="${pageContext.request.contextPath}/moduleResources/dicomecg/MediaFileViewer2.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="allowScriptAccess" value="always" />
			<param name="allowFullScreen" value="true" />
			
			
			<embed
				src="${pageContext.request.contextPath}/moduleResources/dicomecg/MediaFileViewer2.swf"
				quality="high" bgcolor="#869ca7" width="100%" height="100%"
				name="TestViewer" align="middle" play="true" loop="false"
				quality="high" allowScriptAccess="always" allowFullScreen="true"
				type="application/x-shockwave-flash"				
				FlashVars = "contextPath=${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=B12345678920130527211933.dcm"
				pluginspage="http://www.adobe.com/go/getflashplayer">				
			</embed>			
		</object> --%>
       
       
       
       <!--  show in image -->
       <img height=100% width=100% src="${pageContext.request.contextPath}/moduleServlet/dicomecg/ViewEcg?filename=B12345678920130527211933.dcm" alt="Here is show 12 lead ecg picture" ismap />
	</div>
	
	
</div>






<div style="clear: both;"></div>
<%@ include file="/WEB-INF/template/footer.jsp"%>