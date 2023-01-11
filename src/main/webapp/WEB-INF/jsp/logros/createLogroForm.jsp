<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="logros">
    <jsp:body>
        <div style="text-align: center; ">
            <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Crear Logro</h2>
        </div>
        <form:form modelAttribute="logro"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${logro.id}"/>
            <div class="form-group has-feedback">                
                <petclinic:inputField label="Nombre" name="nombre"/>
                <petclinic:inputField label="Descripcion" name="descripcion"/>
                <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
                <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
                <div class="${cssGroup}">
                    <label class="col-sm-2 control-label">Tipo:</label>
            
                    <div class="col-sm-10">
                        <select class="form-control" name = "tipo">
                            <option value="1">Por jugar</option>
                            <option value="2">Por ganar</option>
                            <option value="3">Victorias Leal</option>
                            <option value="4">Victorias Traidor</option>
                            <option value="5">Victorias Mercader</option>
                        </select>
                    </div>
                </div>
                <petclinic:inputField label="Limite" name="limite"/>
                <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
                <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
                <div class="${cssGroup}">
                    <label class="col-sm-2 control-label">Dificultad:</label>
            
                    <div class="col-sm-10">
                        <select class="form-control" name = "dificultad">
                            <option value="1">F&#225;cil</option>
                            <option value="2">Normal</option>
                            <option value="3">Dif&#205;cil</option>
                        </select>
                    </div>
                </div>
            </div>
            <div style="text-align:center; margin-top: 1%;">
                    <button class="btn btn-default" style="font-size: 20px; font-family: sans-serif; "  type="submit" href="/logros/new">Crearlo</button>
            </div>
        </form:form>        
    </jsp:body>
</petclinic:layout>