<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  



<petclinic:layout pageName="home">
    <body style="background-color: #b6b6b6;">
        <div style="text-align: center; justify-content: center; position: relative; top: 30%;  height: 700; background-color: #b6b6b6;">

            <div style=>  
                <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; color: #ca9a4c; font-size: 100; position: relative; top: 220px;" >IDUS MARTII</h2>
            </div>

            <sec:authorize access='hasAuthority("jugador")'>   
                <div style="position: relative; top: 30; left: 520;">
                    <div>
                        <spring:url value="${url}" htmlEscape="true" var="img"/>
                        <img width="100px" height="100px" src="${img}" style="border-radius: 35px; border: 3px solid black;" />
                    </div>
                    <spring:url value="/jugadores/perfil/{userName}" htmlEscape="true" var="misEstadisticas">
                                    <spring:param name="userName" value="${userName}" />
                                </spring:url>
                                <a class="btn btn-default" style="margin-top: 20;" href="${misEstadisticas}">MI PERFIL</a>
                </div>
            </sec:authorize>
            <div style="position: relative; top: 210; font-family: 'Dalek Pinpoint';">
                <spring:url value="/instrucciones" htmlEscape="true" var="como"/>
                <a class="btn btn-default" style="font-size: 36;"  href="${como}">C&#211;MO JUGAR</a>

                <spring:url value="partidas/seleccionar" htmlEscape="true" var="como"/>
                <a class="btn btn-default" style="margin: 60; font-size: 60; padding: 20;" href="${como}">JUGAR</a>

                <spring:url value="/estadisticas" htmlEscape="true" var="como"/>
                <a class="btn btn-default" style="font-size: 36;" href="${como}">ESTAD&#205;STICAS</a>
                <div style="position: relative; top: -30;">
                    <spring:url value="jugadores/espectar/{userName}" htmlEscape="true" var="espectar">
                        <spring:param name="userName" value="${userName}" />
                    </spring:url>
                    <a class="btn btn-default"  href="${espectar}">ESPECTAR</a>
                </div>
            </div>
            
        </div>
    </body>
</petclinic:layout>

