<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="editarPerfil">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div style="text-align: center; ">
            <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Actualizar perfil</h2>
        </div>
        <form:form modelAttribute="jugador"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${jugador.id}"/>
            <div class="form-group has-feedback">                
                <petclinic:inputField label="Nombre" name="firstName"/>
                <petclinic:inputField label="Apellido" name="lastName"/>
                <petclinic:inputField label="Contrase&ntilde;a" name="user.password"/>
            </div>
            <div class="form-group" style="text-align: center;">
                <c:if test = "${jugador.user.username == nombreUsuario}">
                    <div class="col-sm-offset-2 col-sm-10"></div>
                         <button class="btn btn-default" style = "font-family: sans-serif; font-size: 20px;"  href="/jugadores/perfil/${username}" type="submit">Actualizar Perfil</button>
                    </div>
                </c:if>
            </div>
        </form:form>
        <div style="text-align: center; ">
            <a class="btn btn-default" style = "font-family: sans-serif; font-size: 20px;" href="/jugadores/perfil/${username}">Volver al Perfil</a>
        </div>  
    </jsp:body>
</petclinic:layout>