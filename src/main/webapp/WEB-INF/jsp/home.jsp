<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  



<petclinic:layout pageName="home">
    <sec:authorize access="isAuthenticated()">   
        <div>
            <table style="text-align: right; position: relative; top: 30%; width: 100%;">
                <tr>
                    <td>
                        <spring:url value="/jugadores/perfil/{userName}" htmlEscape="true" var="misEstadisticas">
                            <spring:param name="userName" value="${userName}" />
                        </spring:url>
                        <a class="btn btn-default"  href="${misEstadisticas}">MI PERFIL</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <spring:url value="/jugadores/partidas/{userName}" htmlEscape="true" var="misPartidas">
                            <spring:param name="userName" value="${userName}" />
                        </spring:url>
                        <a class="btn btn-default"  href="${misPartidas}">MIS PARTIDAS</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <spring:url value="#" htmlEscape="true" var="misAmigos">
                            <spring:param name="userName" value="${userName}" />
                        </spring:url>
                        <a class="btn btn-default"  href="${misAmigos}">MIS AMIGOS</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <spring:url value="jugadores/logros/{userName}" htmlEscape="true" var="misLogros">
                            <spring:param name="userName" value="${userName}" />
                        </spring:url>
                        <a class="btn btn-default"  href="${misLogros}">MIS LOGROS</a>
                    </td>
                </tr>
            </table>
        </div>
    </sec:authorize>
    
    <div style="text-align: center; position: relative; top: 30%;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; color: #ecb660;";>IDUS MARTII</h2>
        <div>
            <spring:url value="/instrucciones" htmlEscape="true" var="como"/>
            <a class="btn btn-default"  href="${como}">COMO JUGAR</a>

            <spring:url value="partidas/seleccionar" htmlEscape="true" var="como"/>
            <a class="btn btn-default"  href="${como}">JUGAR</a>

            <spring:url value="/estadisticas" htmlEscape="true" var="como"/>
            <a class="btn btn-default"  href="${como}">ESTADISTICAS</a>
        </div>
    </div>
</petclinic:layout>

