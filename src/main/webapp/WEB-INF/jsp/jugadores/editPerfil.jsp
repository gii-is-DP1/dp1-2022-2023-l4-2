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
        <a class="btn btn-default" href="/jugadores/perfil/${username}">Volver al Perfil</a>
        <h2 style = "font-family: 'Dalek Pinpoint', sans-serif;";>
            Actualizar perfil
            
        </h2>
        <form:form modelAttribute="jugador"
                   class="form-horizontal">
            <input type="hidden" name="nombre" value="${jugador.user}"/>
            <div class="form-group has-feedback">                
                <petclinic:inputField label="Nombre" name="firstName"/>
                <petclinic:inputField label="Apellido" name="lastName"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
                     <button class="btn btn-default" type="submit">Actualizar Perfil</button>
                </div>
            </div>
        </form:form>        
    </jsp:body>
</petclinic:layout>