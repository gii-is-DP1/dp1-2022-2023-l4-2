<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="logros">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#birthDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>
            
        </h2>
        <form:form modelAttribute="logro"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${logro.id}"/>
            <div class="form-group has-feedback">                
                <petclinic:inputField label="Nombre" name="nombre"/>
                <petclinic:inputField label="Descripcion" name="descripcion"/>
                <petclinic:inputField label="Tipo" name="tipo"/>
                <petclinic:inputField label="Limite" name="limite"/>
                <petclinic:inputField label="Dificultad" name="dificultad.id"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10"></div>
                     <button class="btn btn-default" type="submit">Update Logro</button>
                </div>
            </div>
        </form:form>        
    </jsp:body>
</petclinic:layout>