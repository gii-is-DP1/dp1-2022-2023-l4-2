<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  



<petclinic:layout pageName="partidas">

    <table style="width: 100%; text-align: center; position: relative; top: 30%;">
        <tr>
            <td>
                <h2 style="font-family: 'Dalek Pinpoint', sans-serif; color: #ecb660;" ;>Que prefiere</h2>
            </td>
        </tr>
        <tr>
            <td>
                <spring:url value="/partidas/new" htmlEscape="true" var="seleccionar" />
                <a class="btn btn-default" href="${seleccionar}">CREAR PARTIDA</a>
            </td>
        </tr>
        <tr>
            <td>
                <spring:url value="/partidas/join" htmlEscape="true" var="unirse" />
                <a class="btn btn-default" href="${unirse}" target="_blank">UNIRSE A UNA PARTIDA</a>
            </td>
        </tr>
    </table>
</petclinic:layout>
