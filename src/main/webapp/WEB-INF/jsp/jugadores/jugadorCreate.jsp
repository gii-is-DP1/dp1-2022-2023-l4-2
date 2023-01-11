<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <div style="text-align: center; ">
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Nuevo Jugador</h2>
    </div>
    <form:form modelAttribute="jugador" class="form-horizontal" id="add-jugador-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="firstName"/>
            <petclinic:inputField label="Apellidos" name="lastName"/>
            <petclinic:inputField label="Nombre de usuario" name="user.username"/>
            <div style="color: red;">
            <c:out value ="${Mensaje1}"/>
            </div>
            <petclinic:inputField label="Contrase&ntilde;a" name="user.password"/>
            <div style="color: red;">
            <c:out value = "${Mensaje2}"/>
            </div>
        </div>
        <div class="form-group">
            <div style="text-align: center; ">
                <button class="btn btn-default" style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 20px;" type="submit">Crear jugador</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
