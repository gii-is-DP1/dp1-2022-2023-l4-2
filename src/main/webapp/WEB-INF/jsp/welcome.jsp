<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="idusmartii" tagdir="/WEB-INF/tags" %>

<link href="http://fonts.cdnfonts.com/css/dalek-pinpoint" rel="stylesheet">
<!doctype html>
<html>
<idusmartii:htmlHeader/>
<spring:url value="/resources/images/idusDeMarzo.jpg" htmlEscape="true" var="idusDeMarzo"/>

<body style="background-image: url(/resources/images/idusDeMarzo.jpg); background-repeat: 
no-repeat; background-attachment: fixed; background-size: cover;" class="welcome">
    <div style="text-align: center; position: relative; top: 30%;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; color: white;";>BIENVENIDO AL IDUS MARTII</h2>
        <spring:url value="/home" htmlEscape="true" var="home"/>
        <a class="btn btn-default"  href="${home}">CONTINUAR</a>
    </div>
</body>


</html>
