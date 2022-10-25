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
            <c:if test="${logro['new']}">New </c:if> Logro
        </h2>
        <form:form modelAttribute="logro"
                   class="form-horizontal">
            <input type="hidden" name="id" value="${logro.id}"/>
            <div class="form-group has-feedback">                
                <petclinic:inputField label="Name" name="name"/>
                <petclinic:inputField label="Description" name="description"/>
                <petclinic:inputField label="Type" name="type"/>
                <petclinic:inputField label="Limit" name="limit"/>
                <petclinic:inputField label="Difficulty" name="difficulty"/>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <c:choose>
                        <c:when test="${logro['new']}">
                            <button class="btn btn-default" type="submit">Add Logro</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-default" type="submit">Update Logro</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </form:form>        
    </jsp:body>
</petclinic:layout>