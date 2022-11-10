<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="idusmartii" tagdir="/WEB-INF/tags" %>

<idusmartii:layout pageName="error">

    <spring:url value="/resources/images/error.png" var="errorImage"/>
    <img src="${errorImage}"/>

    <h2>Ha ocurrido un errorr...</h2>

    <p>${exception.message}</p>

</idusmartii:layout>
