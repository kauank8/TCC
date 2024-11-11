<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleConsultarAgendamentoFuncionario.css"/>'>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"/>
    <title>Perpétua Beleza e Estética - Consultar Seriços</title>
  </head>
  <body>
    <nav class="sidebar close">
      <header>
        <div class="imagem-texto">
          <i class="bx bx-menu sandwich"></i>

          <div class="texto header-texto">
            <span class="salao">Perpétua Beleza e Estética</span>
          </div>
        </div>
      </header>

      <div class="menu-bar">
        <div class="menu">
          <ul class="menu-links">
            <li class="nav-link">
              <a href="#">
                <i class="bx bx-home-alt icon"></i>
                <span class="texto nav-texto">Dashboard</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="#">
                <i class="bx bx-bar-chart-alt-2 icon"></i>
                <span class="texto nav-texto">Revenue</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="#">
                <i class="bx bx-bell icon"></i>
                <span class="texto nav-texto">Norifications</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="#">
                <i class="bx bx-pie-chart-alt icon"></i>
                <span class="texto nav-texto">Analytics</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="#">
                <i class="bx bx-heart icon"></i>
                <span class="texto nav-texto">Likes</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="#">
                <i class="bx bx-wallet icon"></i>
                <span class="texto nav-texto">Wallets</span>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div class="baixo">
        <li class="">
          <a href="#">
            <i class="bx bx-log-out icon"></i>
            <span class="texto nav-texto">Logout</span>
          </a>
        </li>
      </div>
    </nav>

    <div id="error-container"></div>
    <div id="success-container"></div>

    <section class="content">
      <c:if test="${not empty mensagemSucesso}">
			<script src="./resources/js/consultarAgendamentoCliente.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/consultarAgendamentoCliente.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if> 

    <div class="topo-infos">

      <div class="left">

        <div class="bemVindo">
          <div class="titulo-sub">
            <h2>Bem vinda ${funcionario.nome }</h2>
            <p></p>
          </div>
          <img src="./images/logoProvisoria.png" alt="">
        </div>

      </div>
      <div class="right">
        <div class="qtdAgendamentos">
          <h4>Total de agendamentos para hoje: </h4>
          <h3>${qtdAgendamentosDoDia }</h3>
        </div>
      </div>

    </div>

    <form action="consultarAgendamentoFuncionario" method="post">
      <div class="search-bar">
        <h3>Confira seus agendamento de hoje:</h3>
        <div class="search">
          <input type="date" id="data" name="data" value=${data } />
          <button type="submit" class="btn-icon" id="botao" name="botao" value="Pesquisar">
            <i class="bx bx-search icone-pesquisa"></i>
          </button>
        </div>
      </div>
    </form>
	
      <div class="service-list">
      	<c:if test="${not empty agendamentos }">
	      	<c:forEach var="a" items="${agendamentos }">
		        <div class="service-item">
		          <div class="check-info">
		            <div class="service-info">
		            
		            <c:set var="dataOriginal" value="${a.data }" />

						<!-- Extraindo o ano, mês e dia com substring -->
						<c:set var="ano" value="${fn:substring(dataOriginal, 0, 4)}" />
						<c:set var="mes" value="${fn:substring(dataOriginal, 5, 7)}" />
						<c:set var="dia" value="${fn:substring(dataOriginal, 8, 10)}" />
		            
		              <p><strong>${a.horaInicio } - ${a.horaFim }</strong> - ${dia}/${mes}/${ano }</p>
		              <c:forEach var="s" items="${a.servicos }">
			              <p>${s.titulo }</p>
		              </c:forEach>
		            </div>
		          </div>
		          <div class="service-price">
		            <h4>Cliente: ${a.cliente.nome }</h4>
		          </div>
		        </div>
	        </c:forEach>
        </c:if>
      </div>
      
      <c:if test="${empty agendamentos }">
        	<h1>Você não possuir agendamentos nesta data!</h1>
        	<div class="service-list">
      	<c:if test="${not empty proximosAgendamentos }">
      	<h4 >Seus proximos agendamentos marcados são: </h4>
	      	<c:forEach var="a" items="${proximosAgendamentos }">
		        <div class="service-item">
		          <div class="check-info">
		            <div class="service-info">
		            
		            <c:set var="dataOriginal" value="${a.data }" />

						<!-- Extraindo o ano, mês e dia com substring -->
						<c:set var="ano" value="${fn:substring(dataOriginal, 0, 4)}" />
						<c:set var="mes" value="${fn:substring(dataOriginal, 5, 7)}" />
						<c:set var="dia" value="${fn:substring(dataOriginal, 8, 10)}" />
		            
		              <p><strong>${dia}/${mes}/${ano }</strong> - ${a.horaInicio } - ${a.horaFim }</p>
		              <c:forEach var="s" items="${a.servicos }">
			              <p>${s.titulo }</p>
		              </c:forEach>
		            </div>
		          </div>
		          <div class="service-price">
		            <h4>Cliente: ${a.cliente.nome }</h4>
		          </div>
		        </div>
	        </c:forEach>
        </c:if>
        </div>
        	
     </c:if>
    
    </section>

    <script src="./resources/js/consultarAgendamentoFuncionario.js"></script>
  </body>
</html>
