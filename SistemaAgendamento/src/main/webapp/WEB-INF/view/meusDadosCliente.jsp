<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleMeusDadosCliente.css"/>'>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Perpetua Beleza e Estética - Meus Dados Cliente</title>
</head>

<body>
    <nav class="sidebar close">
        <header>
            <div class="imagem-texto">

                <i class='bx bx-menu sandwich'></i>

                <div class="texto header-texto">
                    <span class="salao">Perpetua Beleza e Estética </span>
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
			<script src="./resources/js/meusDadosCliente.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/meusDadosCliente.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if>

        <div class="header">
            <h1>Meus Dados</h1>
        </div>

        <div class="form-container">
            <form action="meusDadosCliente" method="post" onsubmit="return validaCampos()" class="form">

                <div class="inputs">
                    <label class="label-input" for="">
                    	Email:
                        <input type="email" id="emailCadastro" name="email" placeholder="Email" value=${cliente.email }>
                    </label>
                
                    <label class="label-input" for="">
                    	Nome:
                        <input type="text" id="nome" name="nome" placeholder="Nome" value="${cliente.nome }">
                    </label>
                
                    <label class="label-input" for="">
                    	Telefone:
                        <input type="text" id="telefone" name="telefone" placeholder="Numero de telefone" value=${cliente.telefone }>
                    </label>
                
                    <label class="label-input" for="">
                    	Cpf:
                        <input type="text" id="cpf" name="cpf" placeholder="CPF" value=${cliente.cpf } disabled>
                    </label>
                
               		<c:set var="enderecoCompleto" value="${cliente.endereco}" />
                    <c:if test="${not empty enderecoCompleto}">
                        <c:set var="logradouro" value="${fn:substringBefore(enderecoCompleto, ',')}" />
                        <c:set var="numero" value="${fn:substringAfter(enderecoCompleto, ',')}" />
                    </c:if>
                    
                    <label class="label-input" for="">
                    	Logradouro:
                        <input type="text" id="logradouro" name="logradouro" placeholder="Logradouro" value=${logradouro } />
                    </label>
                
                    <label class="label-input" for="">
                    	Numero:
                        <input type="text" id="numero" name="numero" placeholder="Número" value=${numero }>
                    </label>
                
                   <label class="label-input" for="senhaCadastro">
                        Senha:
                        <div>
                            <input type="password" id="senhaCadastro" name="senha" placeholder="Senha" value=${cliente.senha }>
                            <i class="bi bi-eye-slash icone-senha" id="icone-senha" onclick="mostrarSenha('senhaCadastro')"></i>
                        </div>
                    </label>
                    
                </div>

                <div class="button-group">
                    <button type="submit" class="btn" id="botao" name="botao" value="Atualizar">Atualizar</button>
                </div>
            </form>
        </div>

    </section>

    <script src="./resources/js/meusDadosCliente.js"></script>
</body>

</html>