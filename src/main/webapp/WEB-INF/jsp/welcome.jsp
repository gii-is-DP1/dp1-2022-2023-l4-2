<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  



<petclinic:layout pageName="home">
    <h2><fmt:message key="welcome"/></h2>
    <div class="row">
        <div class="col-md-12">
            <spring:url value="/resources/images/pets.png" htmlEscape="true" var="petsImage"/>
            <spring:url value="/resources/images/logoPNG_3.png" htmlEscape="true" var="logo"/>
            <img class="img-responsive" src="${petsImage}"/>
            <img class="img-responsive" src="${logo}"/>
            <spring:url value="/resources/images/idus.png" htmlEscape="true" var="idus"/>
            <img class="img-responsive" src="${idus}">
        </div>
    </div>
</petclinic:layout>
