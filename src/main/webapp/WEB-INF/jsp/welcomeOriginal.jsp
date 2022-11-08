<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  



<petclinic:layout pageName="welcome">
    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>IDUS MARTII</h2>
    <div class="img-button">
        <spring:url value="/resources/images/idusDeMarzo.jpg" htmlEscape="true" var="idusDeMarzo"/>
        <img src="${idusDeMarzo}"/>

        <spring:url value="/home" htmlEscape="true" var="home"/>
        <a class="btn btn-default" href="${home}">CONTINUAR</a>
    </div>
</petclinic:layout>
