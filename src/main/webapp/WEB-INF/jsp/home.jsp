<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  



<petclinic:layout pageName="home">

    <table style="width: 100%; text-align: center; position: relative; top: 30%;">
        <tr>
            <td>
                <h2 style="font-family: 'Dalek Pinpoint', sans-serif; color: #ecb660;" ;>IDUS MARTII</h2>
            </td>
        </tr>
        <tr>
            <td>
                <spring:url value="#" htmlEscape="true" var="jugar" />
                <a class="btn btn-default" href="${jugar}">JUGAR</a>
            </td>
        </tr>
        <tr>
            <td>
                <spring:url value="/instrucciones" htmlEscape="true" var="como" />
                <a class="btn btn-default" href="${como}" target="_blank">COMO JUGAR</a>
            </td>
        </tr>
    </table>
</petclinic:layout>

