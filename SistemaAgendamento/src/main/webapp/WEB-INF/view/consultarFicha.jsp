<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleConsultarFicha.css"/>'>
    <link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"/>
    <title>Perpétua Beleza e Estética - Consultar Ficha</title>
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
			<script src="./resources/js/consultarFicha.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/consultarFicha.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if> 
		
    <form action="consultarFicha" method="post">
      <div class="search-bar">
        <h2>Ficha</h2>
        <!--  
        <div class="search">
          <form action="consultarFicha" method="post" class="form-pesquisar">
            <div class="nome-cpf">
              <input type="text" id="nome-pesquisa" name="nome-pesquisa" placeholder="Nome do Cliente" />
              <input type="text" id="cpf-pesquisa" name="cpf-pesquisa" placeholder="CPF do Cliente" />
            </div>
            <button type="submit" class="btn-icon" id="botao" name="botao" value="Pesquisar">
              <i class="bx bx-search icone-pesquisa"></i>
            </button>
          </form>
        </div>
        -->
      </div>

      <div class="cliente-info">
        <label class="label-input" for="nome">
          Nome:
          <input type="text" id="nome" name="nome" placeholder="Nome" value="${ficha.cliente.nome }" disabled>
        </label>
        <label class="label-input" for="telefone">
          Telefone:
          <input type="text" id="telefone" name="telefone" placeholder="Telefone" value="${ficha.cliente.telefone }" disabled>
        </label>
        <label class="label-input" for="logradouro">
          Logradouro:
          <input type="text" id="logradouro" name="logradouro" placeholder="Logradouro" disabled>
        </label>
        <label class="label-input" for="email">
          Email:
          <input type="text" id="email" name="email" placeholder="Email" value="${ficha.cliente.email }" disabled>
        </label>
        <label class="label-input" for="cpf">
          CPF:
          <input type="text" id="cpf" name="cpf" placeholder="CPF" value="${ficha.cliente.cpf }" disabled>
        </label>
        <label class="label-input" for="numero">
          Número:
          <input type="text" id="numero" name="numero" placeholder="Numero" disabled>
        </label>
      </div>
	</form>
	
      <div class="service-list">
      <c:if test="${not empty agendamentos }">
     	<c:forEach var="a" items="${agendamentos }">
        <div class="service-item">
          <div class="check-info">
            <div class="service-info">
              <h3>Agendamento do dia ${a.data}<br></h3>
            </div>
          </div>
          <div class="service-price">
          <form action="consultarFicha" method="post">
          	<input type="hidden" id="idAgendamento" name="idAgendamento" value="${a.id}" />
            <button type="submit" class="detalhes-btn" name="botao" id="botao" value="Detalhes">
             Detalhes
            </button>
           </form> 
          </div>
          	
          
        </div>
        </c:forEach>
        </c:if>
        <!--  
        <div class="service-item">
          <div class="check-info">
            <div class="service-info">
              <h3>Corte Simples</h3>
              <p>30/10/2024</p>
            </div>
          </div>
          <div class="service-price">
            <button class="detalhes-btn" onclick="abrirModal()">
              Detalhes
            </button>
          </div>
           Modal 
          <div id="modal" class="modal">
            <form action="consultarFicha" method="post">
              <div class="modal-content">
                <i class='bx bx-x-circle' onclick="fecharModal()"></i>
                <h2>Corte Simples</h2>
                <div class="modal-infos">
                  <p><strong>Produto usado: </strong> Shampoo</p>
                  <p><strong>Data: </strong> 29/10/2024</p>
                  <p><strong>Horario: </strong> 10:00</p>
                </div>
                <div class="label-textarea">
                  <strong>Observações:</strong>
                  <textarea name="observacoes" id="observacoes"></textarea>
                </div>
                <input type="submit"  class="atualizar-btn" name="botao" id="botao" value="Atualizar">
              </div>
            </form>
          </div>
        </div> -->
      </div>

  
    
    </section>

    <script src="./resources/js/consultarFicha.js"></script>
  </body>
</html>
