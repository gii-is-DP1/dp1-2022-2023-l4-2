<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:lo2 pageName="chat">
    <jsp:body>
            <c:forEach items="${chat.mensajes}" var="m">
                        <c:choose>
                            <c:when test = "${m.jugador.id == jActual.id}">
                                <div style="text-align: right;">
                                    <c:out value="${m.contenido}"/> 
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: left;">
                                    
                                    ${m.jugador.user.username}:<c:out value="${m.contenido}"/> 
                                </div>
                            </c:otherwise>
                        </c:choose> 
            </c:forEach>

            <button>
                <a class="btn btn-default" href="/chat/escribirMensaje/${id}" target="_blank">Envia un mensaje</a></th>
            </button>
    </jsp:body>    
</petclinic:lo2>
