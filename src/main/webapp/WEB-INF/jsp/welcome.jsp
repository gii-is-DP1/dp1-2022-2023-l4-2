<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<link href="http://fonts.cdnfonts.com/css/dalek-pinpoint" rel="stylesheet">
<!doctype html>
<html>
<petclinic:htmlHeader/>
<spring:url value="/resources/images/idusDeMarzo.jpg" htmlEscape="true" var="idusDeMarzo"/>

<body style="background-image: url(/resources/images/idusDeMarzo.jpg); background-repeat: 
no-repeat; background-attachment: fixed; background-size: cover;" class="welcome">
    <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; padding: 1%;  height: 70%; font-size: xx-large;">
        <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; color: white;">
            BIENVENIDO AL IDUS MARTII
            <div style="text-align: center; margin: 5%;">
                <spring:url value="/home" htmlEscape="true" var="home"/>
                <a class="btn btn-default"  href="${home}">INICIO</a>
            </div>
        </div>
    </div>
</body>


</html>
