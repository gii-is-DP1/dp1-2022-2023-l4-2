<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="idusmartii" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>
<link href="http://fonts.cdnfonts.com/css/dalek-pinpoint" rel="stylesheet">

<!doctype html>
<html>
<idusmartii:htmlHeader/>

<body style = "font-family: 'Dalek Pinpoint', sans-serif;";>
<idusmartii:bodyHeader menuName="${pageName}"/>

<div class="container-fluid">
    <div class="container xd-container">
	<c:if test="${not empty message}" >
	<div class="alert alert-${not empty messageType ? messageType : 'info'}" role="alert">
  		<c:out value="${message}"></c:out>
   		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    		<span aria-hidden="true">&times;</span>
  		</button> 
	</div>
	</c:if>

        <jsp:doBody/>

        <idusmartii:pivotal/>
    </div>
</div>
<idusmartii:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
