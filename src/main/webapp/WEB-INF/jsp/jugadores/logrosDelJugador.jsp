<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

    

<petclinic:layout pageName="logros">
    <div style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 100px;text-align: center;">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>Logros de <c:out value ="${username}"/></h2>
    </div>


    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripcion</th>
            <th>Tipo</th>
            <th>Limite</th>
            <th>Dificultad</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr style = "text-align: center; ";>
                <td>
                    <c:out value="${logro.nombre}"/>
                </td>
                <td>
                    <c:out value="${logro.descripcion}"/>
                </td>
                <td>
                    <c:out value="${logro.tipo}"/>
                </td>
                <td>
                    <c:out value="${logro.limite}"/>
                </td>
                <td>
                    <c:out value="${logro.dificultad}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
