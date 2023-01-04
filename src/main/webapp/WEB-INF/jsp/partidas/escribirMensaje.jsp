<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:lo2 pageName="chat">
    <jsp:body>
        <form:form modelAttribute="mensaje" class="form-horizontal">
                <div class="form-group has-feedback">                
                    <petclinic:inputField label="Escribe algo:" name="contenido"/>
                </div>
                <div class="form-group">
                        
                            <button class="btn btn-default"  type="submit">Enviar</button>
                        
                </div>
            </form:form>    
    </jsp:body>    
</petclinic:lo2>
