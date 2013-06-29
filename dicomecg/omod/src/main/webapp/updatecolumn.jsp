<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<div>
	<table>
		<td>
			<form method="POST" name="confirm">
				<table>
					<c:choose>
						<c:when test ="${not empty confirmEdit}">
							<input type="hidden" name="id" value="${confirmEdit.id}" >
							<tr>
								<td>Patient Id</td>
								<td><input type="text" name="patientId" value="${confirmEdit.patientId}"></td>
							</tr>
							<tr>
								<td>Patient Identifier</td>
								<td><input type="text" name="identifier" value="${confirmEdit.identifier}"></td>
							</tr>
							<tr>
								<td>Confirm Time</td>
								<td><input type="text" name="confirmTime" value="${confirmEdit.confirmTime}"></td>
							</tr>	
							<tr>
								<td>Confirm Name</td>
								<td><input type="text" name="confirmName" value="${confirmEdit.confirmName}"></td>
							</tr>	
							<tr>
								<td>Comment</td>
								<td><input type="text" name="comment" value="${confirmEdit.comment}"></td>
							</tr>	
							<tr>
								<td>Filename</td>
								<td><input type="text" name="filename" value="${confirmEdit.filename}"></td>
							</tr>	
							<tr>
								<td>Mail</td>
								<td><input type="text" name="mail" value="${confirmEdit.mail}"></td>
							</tr>
							<tr>
								<td></td><td><input type="submit" value="Save Changes" /></td>
							</tr>														
						</c:when>
						<c:otherwise>
							<tr>
								<td>Patient Id</td>
								<td><input type="text" name="householdDefinitionsCode" value="${patientId}" /></td>
							</tr>
							<tr>
								<td>Patient Identifier</td>
								<td><input type="text" name="identifier" value="${identifier}"></td>
							</tr>
							<tr>
								<td>Confirm Time</td>
								<td><input type="text" name="confirmTime" value="${confirmTime}"></td>
							</tr>	
							<tr>
								<td>Confirm Name</td>
								<td><input type="text" name="confirmName" value="${confirmName}"></td>
							</tr>	
							<tr>
								<td>Comment</td>
								<td><input type="text" name="comment" value="${comment}"></td>
							</tr>	
							<tr>
								<td>Filename</td>
								<td><input type="text" name="filename" value="${filename}"></td>
							</tr>	
							<tr>
								<td>Mail</td>
								<td><input type="text" name="mail" value="${mail}"></td>
							</tr>
							<tr>
								<td></td><td><input type="submit" value="Add New" /></td>
							</tr>						
						</c:otherwise>					
					</c:choose>
				</table>			
			</form>
		</td>
		
		<td width="1" bgcolor="#C0C0C0"><BR></td>
		<td valign="top">
				<table cellpadding="5" width="100%" id="mytable">
					<tr>
						<th class="tbClass">Confirm Id</th>
						<th class="tbClass">Patient Id</th>
						<th class="tbClass">Identifier</th>
						<th class="tbClass">Confirm Time</th>
						<th class="tbClass">Confirm Name</th>
						<th class="tbClass">Comment</th>
						<th class="tbClass">Filename</th>
						<th class="tbClass">Mail</th>
						<th class="tbClass">Action</th>
					</tr>
					<c:forEach var="confirmall" items="${confirmall}">
						<form method="POST" name="${confirmall.id}">
						<tr>
							<td class="tdClass">${confirmall.id}</td>
							<td class="tdClass">${confirmall.patientId}</td>
							<td class="tdClass">${confirmall.identifier}</td>
							<td class="tdClass">${confirmall.confirmTime}</td>
							<td class="tdClass">${confirmall.confirmName}</td>
							<td class="tdClass">${confirmall.comment}</td>
							<td class="tdClass">${confirmall.filename}</td>
							<td class="tdClass">${confirmall.mail}</td>
							<td class="tdClass">
								<input type="hidden" name="id" id="${confirmall.id}" value="${confirmall.id}" />
								<input type="submit" value="Edit" />
							</td>
						</tr>
						</form>
					</c:forEach>
				</table>
			</td>
		
		
	</table>



</div>


<%@ include file="/WEB-INF/template/footer.jsp"%>