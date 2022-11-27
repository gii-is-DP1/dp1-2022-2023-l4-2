<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:lo2 pageName="chat">
    <div>
        Chat de la partida ${pActual.id}
        <c:forEach items="${chat}" var="m">
                    <c:choose>
                        <c:when test = "${m.jugador.id == jActual.id}">
                            <div style="text-align: right;">
                                <c:out value="${m.contenido}"/> 
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div style="text-align: left;">
                                ${m.jugador.user.username}: 
                                <c:out value="${m.contenido}"/> 
                            </div>
                        </c:otherwise>
                    </c:choose> 
        </c:forEach>
    </div>

    <form:form modelAttribute="mensaje" class="form-horizontal">
            <div class="form-group has-feedback">                
                <petclinic:inputField label="Escribe algo:" name="contenido"/>
            </div>
            <div class="form-group">
                    
                         <button class="btn btn-default" type="submit">Enviar</button>
                    
            </div>
        </form:form>        
</petclinic:lo2>
