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
                        <a href="#">
                            <i class='bx bx-home-alt icon'></i>
                            <span class="texto nav-texto">Dashboard</span>
                        </a>
                    </li>
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-bar-chart-alt-2 icon'></i>
                            <span class="texto nav-texto">Revenue</span>
                        </a>
                    </li>
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-bell icon'></i>
                            <span class="texto nav-texto">Norifications</span>
                        </a>
                    </li>
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-pie-chart-alt icon'></i>
                            <span class="texto nav-texto">Analytics</span>
                        </a>
                    </li>
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-heart icon'></i>
                            <span class="texto nav-texto">Likes</span>
                        </a>
                    </li>
                    <li class="nav-link">
                        <a href="#">
                            <i class='bx bx-wallet icon'></i>
                            <span class="texto nav-texto">Wallets</span>
                        </a>
                    </li>
                </ul>
            </div>

        </div>

        <div class="baixo">
            <li class="">
                <a href="#">
                    <i class='bx bx-log-out icon'></i>
                    <span class="texto nav-texto">Logout</span>
                </a>
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

                    <input type="submit" class="btn" name="botao" id="botao" value="Confirmar">
                </form>
            </div>
    </section>

    <script src="./resources/js/cancelarHorario.js"></script>
</body>

</html>