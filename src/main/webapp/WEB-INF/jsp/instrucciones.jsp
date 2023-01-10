<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  



<petclinic:layout pageName="instrucciones">
    <table style="width: 100%; text-align: center; position: relative; top: 30%;">
        <tr>
            <td>
                <div style="margin: 2%;">
                    <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>C&#211;MO JUGAR</h2>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <iframe width="50%" height="350px" src="https://www.youtube.com/embed/1Rl2DWWoBmI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </td>
        </tr>
        <tr>
            <td>
                <div style="margin: 10%; font-size: xx-large;">
                    <spring:url value="/resources/docs/instrucciones.pdf" htmlEscape="true" var="pdf"/>
                    <a class="btn btn-default" style="font-size: 20px; font-family: 'Dalek Pinpoint', sans-serif;"  href="${pdf}" target="_blank">INSTRUCCIONES EN PDF</a>
                </div>
            </td>
        </tr>
    </table>
</petclinic:layout>
