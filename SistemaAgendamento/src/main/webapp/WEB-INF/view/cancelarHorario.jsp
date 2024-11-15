<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.LocalDate" %>	
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/styleCancelarHorario.css">
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleCancelarHorario.css"/>'>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>Perpétua Beleza e Estética - Horarios</title>
</head>

<body>
    <nav class="sidebar close">
        <header>
            <div class="imagem-texto">

                <i class='bx bx-menu sandwich'></i>

                <div class="texto header-texto">
                    <span class="salao">Perpétua Beleza e Estética</span>
                </div>
            </div>
        </header>

        <div class="menu-bar">
        <div class="menu">
          <ul class="menu-links">
            <li class="nav-link">
              <a href="consultarAgendamentoFuncionario">
                <i class='bx bxs-calendar icon'></i>
                <span class="texto nav-texto">Meus Agendamentos</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="consultarCliente">
                <i class='bx bx-smile icon'></i>
                <span class="texto nav-texto">Clientes</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="cancelarHorario">
                <i class='bx bx-time-five icon'></i>
                <span class="texto nav-texto">Meu horário</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="cadastrarServicoProprietaria">
                <i class='bx bx-cut icon'></i>
                <span class="texto nav-texto">Cadastrar Serviços</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="cadastroFuncionarioProprietaria">
                <i class='bx bxs-user-plus icon'></i>
                <span class="texto nav-texto">Funcionarios</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="cadastroProdutoProprietaria">
                <i class='bx bxs-package icon'></i>
                <span class="texto nav-texto">Produtos</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="meusDadosFuncionario">
                <i class='bx bx-user-circle icon'></i>
                <span class="texto nav-texto">Meus Dados</span>
              </a>
            </li>
          </ul>
        </div>
      </div>

       <div class="baixo">
	        <li class="">
	         <form action="login" method="POST" >
	          <button type="submit"  id="botao" name="botao" value="Logout">
	            <i class="bx bx-log-out icon"></i>
	            <span class="texto nav-texto">Logout</span>
	          </button>
	          </form>
	        </li>
      </div>
    </nav>

    <div id="error-container"></div>
	<div id="success-container"></div>

    <section class="content">
    
    <c:if test="${not empty mensagemSucesso}">
        <script src="./resources/js/cancelarHorario.js"></script>
        <script>
          displaySuccessMessage("${mensagemSucesso}");
        </script>
      </c:if>

      <c:if test="${not empty mensagemErro}">
        <script src="./resources/js/cancelarHorario.js"></script>
        <script>
          displayErrorMessage("${mensagemErro}");
        </script>
      </c:if> 
      
        <h2>Cancelar Horário</h2>

            <div class="form-container">
                <form id="formSelect" action="cancelarHorario" method="post" onsubmit="return validaCampos()" class="form">
                	<script>
				          function submitForm() {
				            document.getElementById("formSelect")
				                .submit();
				            console.log('Teste')
				          }
        			</script>

                    <div class="label-input-btn">
                        <div class="label-input">
                            <label for="data">Seleciona uma data:</label>
                            <input type="date" id="data" name="data" value="${data }" onchange="submitForm()">
                        </div>
    
                        <button type="button" class="btn" id="botaoSelecionarDiaCompleto">Selecionar o dia completo</button>

                    </div>
					
					<c:if test="${not empty horarios }">
                    <table class="tabela-horarios">
                        <thead>
                            <tr>
                                <th>Selecionar</th>
                                <th>Horário</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="h" items="${horarios}">
	                            <tr>
	                            	<td><input type="checkbox" name="horarios" value="${h.id}"></td>
	                            	<td>${fn:substring(h.hora.toString(), 0, 5)}</td>
	                            </tr>	
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                 <div class="label-textarea">
                  <strong>Justificativa:</strong>
                  <textarea name="justificativa" id="justificativa" style="resize: none;"></textarea>
                </div>

                    <input type="submit" class="btn" name="botao" id="botao" value="Confirmar">
                </form>
            </div>
    </section>

    <script src="./resources/js/cancelarHorario.js"></script>
</body>

</html>