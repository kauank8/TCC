<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleConsultarAgendamentoCliente.css"/>'> 
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

    <form action="consultarAgendamentoCliente" method="post" onsubmit="return validaCampos()" class="form">
      <div class="search-bar">
        <h2>Agendamentos</h2>
        <div class="search">
          <input type="date" id="data" name="data" value=${data } />
          <button type="submit" class="btn-icon" id="botao" name="botao" value="Pesquisar">
            <i class="bx bx-search icone-pesquisa"></i>
          </button>
        </div>
      </div>

      <div class="service-list">
      <c:forEach var="a" items="${agendamentos }">
        <div class="service-item">
          <div class="check-info">
          
            <div class="service-info">
            <c:forEach var="s" items="${a.servicos }">
              <h3>${s.titulo}</h3>
              
             </c:forEach>
             <p>${a.horaInicio } - ${a.horaFim }</p>
            </div>
          
          </div>
          <div class="service-price">
            <p>${a.valorTotal }</p>
            <button type="submit" class="btn" id="Cancelar" name="Cancelar" value="${a.id }">
            Cancelar
          	</button>
          </div>
        </div>
        </c:forEach>
        
        <c:if test="${empty agendamentos }">
        	<h1>Você não possuir agendamentos nesta data!</h1>
        	<input type="submit" class="btn" id="botao" name="botao" value="Deseja agendar um agendamento?"/>
        	 
        </c:if>
      </div>

    </form>
    
    </section>

    <script src="./resources/js/consultarAgendamentoCliente.js"></script>
  </body>
</html>
