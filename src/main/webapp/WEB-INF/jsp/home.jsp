<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  



<petclinic:layout pageName="home">
    <sec:authorize access="isAuthenticated()">
        
        <div style="text-align: right; position: relative; top: 30%;">
            <spring:url value="/home" htmlEscape="true" var="misEstadisticas"/>
            <a class="btn btn-default"  href="${misEstadisticas}">MIS ESTADISTICAS</a>
            
            <spring:url value="/home" htmlEscape="true" var="misPartidas"/>
            <a class="btn btn-default"  href="${misPartidas}">MIS PARTIDAS</a>

            <spring:url value="/home" htmlEscape="true" var="misAmigos"/>
            <a class="btn btn-default"  href="${misAmigos}">MIS AMIGOS</a>
        </div>
    </sec:authorize>
    


    <div style="text-align: center; position: relative; top: 30%;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; color: #ecb660;";>IDUS MARTII</h2>
        <div>
            <spring:url value="/home" htmlEscape="true" var="jugar"/>
            <a class="btn btn-default"  href="${jugar}">JUGAR</a>
            <div>
                <spring:url value="/resources/docs/instrucciones.pdf" htmlEscape="true" var="como"/>
                <a class="btn btn-default"  href="${como}" target="_blank">COMO JUGAR</a>
            </div>
        </div>
    </div>

    
</petclinic:layout>

