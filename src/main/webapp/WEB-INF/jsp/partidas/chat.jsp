<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:lo2 pageName="chat">
    <jsp:body>

            <div style="text-align: center; ">
                <h2 style = "font-family: 'Dalek Pinpoint', sans-serif; font-size: 40px; margin-bottom: 3%;";>Chat de la partida</h2>
            </div>
        
        <div style="background-color: rgb(199, 199, 199); border: 1px solid;">
            <c:forEach items="${chat.mensajes}" var="m">
                        <c:choose>
                            <c:when test = "${m.jugador.id == jActual.id}">
                                <div style="text-align: right; border: .5px solid; font-size: 35px;">
                                    Yo:&#160;${m.contenido}
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div style="text-align: left; border: .5px solid; font-size: 35px;">
                                    
                                    ${m.jugador.user.username}:<c:out value="${m.contenido}"/> 
                                </div>
                            </c:otherwise>
                        </c:choose> 
            </c:forEach>

        </div>
            <div style="text-align:center; margin-top: 1%;">
                <a class="btn btn-default" style="font-size: 15px; font-family: sans-serif; "  href="/chat/escribirMensaje/${id}" target="_blank">Envia un mensaje</a>
            </div>
    </jsp:body>    
</petclinic:lo2>
