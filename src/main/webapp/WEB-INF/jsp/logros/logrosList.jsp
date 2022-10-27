<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="logros">
    <h2>Logros</h2>

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
            <tr>
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
                <td> 
                    <a href="/logros/edit/${logro.id}"> 
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                    </a>       
                </td>
                <td> 
                    <a href="/logros/delete/${logro.id}"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default" href="/logros/new">Crear logro</a>

</petclinic:layout>
