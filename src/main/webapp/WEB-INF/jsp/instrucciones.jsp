<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  



<petclinic:layout pageName="instrucciones">
    <table style="width: 100%; text-align: center; position: relative; top: 30%;">
        <tr>
            <td><h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>COMO JUGAR</h2></td>
        </tr>
        <tr>
            <td>
                <spring:url value="/resources/docs/instrucciones.pdf" htmlEscape="true" var="pdf"/>
                <a class="btn btn-default"  href="${pdf}" target="_blank">PDF</a>
            </td>
        </tr>
        <tr>
            <td>
                <iframe width="50%" height="315" src="https://www.youtube.com/embed/1Rl2DWWoBmI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            </td>
        </tr>
        <tr>
            <td>
                <iframe src="/resources/docs/instrucciones.pdf" width="70%" height="600px"></iframe>
            </td>
        </tr>  
    </table>
</petclinic:layout>