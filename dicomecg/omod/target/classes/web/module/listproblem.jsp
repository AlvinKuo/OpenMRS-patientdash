<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/dwr/interface/DWRDicomEcgService.js" />
<%-- <openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/dicomecg/jquery.js" /> --%>

<script type="text/javascript" >

function Addother(){
	
	document.form1.comments.value += document.form1.oname.value + ","; 
}

function getConcept(newConceptId){
	
	var newConceptId = newConceptId.value;	
	/* DWRDicomEcgService.getConcept(newConceptId); */
	DWRDicomEcgService.getConcept(newConceptId, function(){
		//Add here the scripts that update the page
		
		var newConcept;				
		showLayer('edit');
		hideLayer('comments');
		return newConcept;
		
	});
	
}



</script>

<p>Hello ${user.systemId}!</p>

<form method="POST" name="form1">

Concept Dictionary: <openmrs_tag:conceptField formFieldName="newConceptId" ></openmrs_tag:conceptField>
<input type="submit" value="View" onclick="getConcept(newConceptId);" />



 <div name="edit">
	<table>	 
	 	<tr>
	 		<th>Concept ID</th>
	 		<td>${ newConcept.conceptId} </td> 
	 	</tr>
	 
	 	<tr>
	    	<th>Name</th>	    	    
	  	</tr>
	  	<tr>
	  		<td>
	  			<input name="oname" type="text" id="oname" value = "${newConcept.name}">	  
	  		</td>		 
	  		<td>
	  			<input type="button" value="Add" onclick="Addother();" />			
	  		</td>
	  	</tr>
	  	</table>	
</div>


<div name="comment">

	<textarea class="comments" name="comments" cols="30" rows="20" style=" font-size:12px; 
		background-color:#FEFF91;  border:double" >	
	</textarea>	

</div>
</form>

<%@ include file="/WEB-INF/template/footer.jsp"%>