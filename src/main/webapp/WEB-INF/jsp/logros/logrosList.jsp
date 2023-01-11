<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="logros">
    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Logros</h2>
    </div>

    <table id="logrosTable" class="table table-striped" style="border: 1px solid">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Descripci&#243;n</th>
            <th>Tipo</th>
            <th>L&#205;mite</th>
            <th>Dificultad</th>
            <sec:authorize access='hasAuthority("admin")' > 
                <th>Editar</th> 
            </sec:authorize>   
            <sec:authorize access='hasAuthority("admin")' > 
                <th>Eliminar</th> 
            </sec:authorize>   
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
                    <sec:authorize access='hasAuthority("admin")' > 
                        <a href="/logros/edit/${logro.id}"> 
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>                            
                        </a>    
                    </sec:authorize>   
                </td>
                <sec:authorize access='hasAuthority("admin")' > 
                <td> 
                    <a href="/logros/delete/${logro.id}"> 
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                    </a>      
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div style="text-align:center; margin-top: 1%;">
        <sec:authorize access='hasAuthority("admin")' >
            <a class="btn btn-default" style="font-size: 20px; font-family: sans-serif; "  href="/logros/new">Crear nuevo logro</a>
        </sec:authorize>
    </div>
</petclinic:layout>
