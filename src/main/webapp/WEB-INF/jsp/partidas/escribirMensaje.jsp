<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:lo2 pageName="chat">
    <jsp:body>
        <form:form modelAttribute="mensaje" class="form-horizontal">
            <div style="text-align: center; ">
                <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Escribir mensajes</h2>
            </div>

                <div class="form-group has-feedback" style="font-size: 20px;">                
                    <petclinic:inputField label="Escribe algo:" name="contenido"/>
                </div>
                <div style="text-align:center; margin-top: 1%;">
                    <button class="btn btn-default" style="font-size: 15px; font-family: sans-serif; font-size: 25px; "  type="submit">Enviar</button>
                </div>
            </form:form>    
    </jsp:body>    
</petclinic:lo2>
