<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  



<petclinic:layout pageName="partidas">

    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 50px; margin-bottom: 1%;";>&#191;Qu&#233; desea?</h2>
    </div>

    <div style="display: flex; flex-direction:column; align-items:center; justify-content: center; padding: 0%;  width:100%;">
        <table>
            <tr>
                
                <td>
                    
                    <div style="text-align:center;">
                        <spring:url value="/resources/images/logo_big.png" var="logo" />
                        <img style ="width: 70%;  height: 100%;" src="${logo}" />
                    </div>
                    <div style="text-align:center; margin-top: 5%;">
                        <spring:url value="/partidas/new" htmlEscape="true" var="seleccionar" />
                        <a class="btn btn-default" style="font-size: 35px; font-family: 'Dalek Pinpoint', sans-serif; "  href="${seleccionar}">Crear una partida</a>
                    </div>
                    <div style="text-align:center; margin-top: 5%;">
                        <spring:url value="/partidas/join" htmlEscape="true" var="unirse" />
                        <a class="btn btn-default" style="font-size: 35px; font-family: 'Dalek Pinpoint', sans-serif; "  href="${unirse}">Unirse a una partida</a>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</petclinic:layout>
