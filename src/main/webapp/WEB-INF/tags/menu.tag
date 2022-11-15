<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	
	<div class="container">

		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<div class="navbar-header">
					<a class="navbar-brand" href="<spring:url value="/home" htmlEscape="true" />"><span></span></a>
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-navbar">
					<span class="sr-only"><os-p>Toggle navigation</os-p></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
					</button>
				</div>

				<petclinic:menuItem active="${name eq 'home'}" url="/home"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Inicio</span>
				</petclinic:menuItem>

				<sec:authorize access='hasAuthority("admin")' > 
					<petclinic:menuItem active="${name eq 'partidas'}" url="/partidas?page=0"
						title="partidas">
						<span class="glyphicon glyphicon-knight" aria-hidden="true"></span>
						<span>Partidas</span>
					</petclinic:menuItem>
					<petclinic:menuItem active="${name eq 'jugadores'}" url="/jugadores"
						title="jugadores">
						<span class="glyphicon glyphicon-bishop" aria-hidden="true"></span>
						<span>Jugadores</span>
					</petclinic:menuItem>
				</sec:authorize>

					<petclinic:menuItem active="${name eq 'logros'}" url="/logros"
						title="logros">
						<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
						<span>Logros</span>
					</petclinic:menuItem>

					
					<petclinic:menuItem active="${name eq 'estadisticas'}" url="/estadisticas"
						title="estadisticas">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Estadisticas</span>
					</petclinic:menuItem>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Iniciar Sesion</a></li>
					<li><a href="<c:url value="/jugadores/new" />">Registrarse</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-cog"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span></a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Cerrar Sesion</a>
											</p>
										</div>
									</div>
								</div>
							</li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>

