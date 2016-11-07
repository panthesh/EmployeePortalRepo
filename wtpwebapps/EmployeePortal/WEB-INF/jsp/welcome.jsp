<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<title>Employee Details</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script type="text/javascript">
var PATH = '${pageContext.request.contextPath}';

$("tr").not(':first').hover(
		  function () {
		    $(this).css("background","gainsboro");
		  }, 
		  function () {
		    $(this).css("background","");
		  }
		);
		
var changeEmployeesAccount = function($this) {
	$this = $($this);
	$.post(PATH + '/retrieveEmployee/' + $this.val(),
			function(data) {
				$('div#employee-detail').replaceWith(data)
			}
	);
}
</script>
</head>
<body>

<html>
<div id="employee-detail">
	<div>
		<table style="width: 100%;font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
			border-bottom: 1pt solid red;">
			<tbody>
				<tr>
					<td style="width: 10%">Employee Id:</td>
					<td><select id="accounIdList" name="accounIdList"
						onchange="changeEmployeesAccount(this)" >
							<option value="" label="" />
							<c:forEach items="${employeeIds}" var="employeeId">
								<option value="${employeeId}" ${employee.id eq employeeId ? 'selected="selected"' : ''}>${employeeId}</option>
							</c:forEach>
					</select></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br/>
	<c:if test="${not empty employee}">
	<div>
		<table style="width: 100%;" >
			<tbody>
				<tr>
					<td style="width: 15%;">Employee Name:</td>
					<td>
							${employee.firstName}&nbsp;${employee.lastName}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Title:</td>
					<td>
							${employee.title}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Group Id:</td>
					<td>
							${employee.groupId}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Group Name:</td>
					<td>
							${employee.groupName}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Manager Id:</td>
					<td>
							${employee.managerId}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Manager Name:</td>
					<td>
							${employee.managerName}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Emergency Contact Name:</td>
					<td>
							${employee.emergencyContactName}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Emergency Contact Number:</td>
					<td>
							${employee.emergencyContactNumber}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Residential Address:</td>
					<td>
							${employee.address.streetName1},&nbsp;<c:if test="${not empty employee.address.streetName2}">${employee.address.streetName2},&nbsp;</c:if>
							${employee.address.city},&nbsp;${employee.address.state},&nbsp;${employee.address.zip},&nbsp;${employee.address.country}					
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Medium User Id:</td>
					<td>
							${employee.mediumUserId}
					</td>
				</tr>
				<tr>
					<td style="width: 15%;">Blog Entries:</td>
					<td>
					</td>
				</tr>
				<c:forEach var="item" items="${employee.mediumFeed.items}">
					<tr>
						<td></td>
						<td style="width: 85%;">
								<a href='<c:url value="${item.guid}"/>'>
									${item.title}
								</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
</div>
</html>
</body>				
