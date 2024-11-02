<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleAgendarServico.css"/>'> 
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />
    <title>Perpétua Beleza e Estética - Agendar Serviços</title>
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
        <script src="./resources/js/agendarServico.js"></script>
        <script>
          displaySuccessMessage("${mensagemSucesso}");
        </script>
      </c:if>

      <c:if test="${not empty mensagemErro}">
        <script src="./resources/js/agendarServico.js"></script>
        <script>
          displayErrorMessage("${mensagemErro}");
        </script>
      </c:if> 
      
      <form id="formSelect" action="agendarServico" method="post" class="form" onsubmit="return validaCampos()">
        <h2>Agendar Serviços</h2>

        <!-- Tentativa de fazer que quando o select for mudado 
        ele já atualize o nome, a rede social e perfi profissional
        do funcionario selecionado -->

        <script>
          function submitForm() {
            document.getElementById("formSelect")
                .submit();
            console.log('Teste')
          }
        </script>

        <div class="data-profissional">
          <input type="date" name="data" id="data" value=${data } onchange="submitForm()" />
          <!--  <form id="formSelect"  action="agendarServico" method="post">-->
          <select name="funcionario" id="funcionario" onchange="submitForm()">
            <option value="" disabled selected>Selecione a profissional</option>
              <c:forEach var="f" items="${funcionarios}">
                <option value="${f.id}"
                <c:if test="${funcionario.id == f.id}">selected</c:if>>
            	${f.nome}</option>
            </c:forEach>
          </select>
        </div>
		
		
        <label class="perfilProfissional" for="perfilProfissional">
          <!--  <strong><c:if test="${empty funcionario}">Nome - <a href="#" target="_blank">Rede Social Profissional</a> </c:if> </strong> 
          <strong><c:if test="${not empty funcionario}">${funcionario.nome } - <a href="https://www.youtube.com/" target="_blank">Rede Social Profissional</a> </c:if> </strong>
          <textarea name="perfilProfissional" id="perfilProfissional" disabled > -->
           <strong>
        	<c:choose>
            <c:when test="${empty funcionario}">
                Nome - <a href="#" target="_blank">Rede Social Profissional</a>
            </c:when>
            <c:otherwise>
                ${funcionario.nome} - <a href="https://www.youtube.com/" target="_blank">Rede Social Profissional</a>
            </c:otherwise>
       		 </c:choose>
   			</strong>
   			<textarea name="perfilProfissional" id="perfilProfissional" disabled > ${funcionario.perfil }
          	</textarea>
        </label>

        <div class="selecione-horario">
          <strong>Selecione um horário:</strong> 
        </div>

        <div class="carrossel-horarios">
          
          <i class="icone-horarios bx bxs-caret-left-circle"></i>

          <div class="swiper horarios">
            <div class="swiper-wrapper">
            
              <c:choose>
            	<c:when test="${empty horarios}">
               		<c:choose>
			            <c:when test="${not empty funcionario}">
			                Não há mais horários disponíveis para o funcionário selecionado nesta data.
			            </c:when>
			            <c:otherwise>
			                Selecione um funcionário e uma data para conferir os horários disponíveis.
			            </c:otherwise>
        			</c:choose>
            	</c:when>
    
              <c:otherwise>
              	<c:forEach var="h" items="${horarios }">
              		<div class="swiper-slide horario">
	              	  	<input type="radio" id="horarioSelecionado" name="horarioSelecionado" value="${h.id}" />
	                	${h.hora}
	                </div>
	             </c:forEach>
              </c:otherwise>
       		 </c:choose>
                
            </div>
          </div>

          <i class="icone-horarios bx bxs-caret-right-circle"></i>

        </div>

        <div class="servicos">
        <c:forEach var="s" items="${servicos }">
          <div class="service-item">
            <div class="check-info">
              <div class="service-info">
                <h3>${s.titulo }</h3>
                <p>${s.duracao } Minutos</p>
                <!--  
                <select name="produto_${s.id}" id="produto_${s.id}">
                        <option value="" disabled selected>
                            Selecione o produto
                        </option>
                        <c:forEach var="produto" items="${s.produtos}">
                            <option value="${produto.id}">${produto.nome}</option>
                        </c:forEach>
                    </select>
                    -->
              </div>
            </div>
            <div class="service-price">
              <p>${s.preco}</p>
              <p>09:00 - 10:20</p>
            </div>
            </div>
            </c:forEach>
          </div>

         <!--  <div class="service-item">
            <div class="check-info">
              <div class="service-info">
                <h3>Corte Simples</h3>
                <p>80 Minutos</p>
                <select name="produto" id="produto">
                  <option value="" disabled selected>
                    Selecione o produto
                  </option>
                  <option value="##">Shampoo</option>
                  <option value="##">Condicionador</option>
                  <option value="##">Creme</option>
                </select>
              </div>
            </div>
            <div class="service-price">
              <p>R$ 50,00</p>
              <p>09:00 - 10:20</p>
            </div>
          </div>
        </div>
        --> 

        <div class="total-previsto">
          <label for="preco"> Total previsto: </label>
          <strong> ${precoTotal } </strong>
        </div>

        <input type="submit" class="confirm-btn" id="botao" name="botao" value="Confirmar"/>
      </form>
    </section>

    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="./resources/js/agendarServico.js"></script>
  </body>
</html>
