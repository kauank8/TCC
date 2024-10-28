<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
     <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleMeusDadosFuncionario.css"/>'>
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <title>Perpetua Beleza e Estética - Meus Dados Funcionario</title>
  </head>

  <body>
    <nav class="sidebar close">
      <header>
        <div class="imagem-texto">
          <i class="bx bx-menu sandwich"></i>

          <div class="texto header-texto">
            <span class="salao">Perpetua Beleza e Estética</span>
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
			<script src="./resources/js/meusDadosFuncionario.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/meusDadosFuncionario.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if>
      <div class="header">
        <h1>Meus Dados</h1>
      </div>

      <div class="form-container">
        <form form action="meusDadosFuncionario" method="post" onsubmit="return validaCampos()" class="form">
          <div class="inputs">
            <label class="label-input" for="nome">
            	Nome:
              <input type="text" id="nome" name="nome" placeholder="Nome" value="${funcionario.nome }"/>
            </label>

            <label class="label-input" for="email">
            	Email:
              <input type="email" id="email" name="email" placeholder="Email" value="${funcionario.email }"/>
            </label>

            <label class="label-input" for="telefone">
            	Telefone:
              <input
                type="text"
                id="telefone"
                name="telefone"
                placeholder="Número de telefone"
                value="${funcionario.telefone }"
              />
            </label>

            <label class="label-input" for="cpf">
            	Cpf:
              <input type="text" id="cpf" name="cpf" placeholder="CPF"  value="${funcionario.cpf }" disabled />
            </label>

            <label class="label-input" for="redeSocial">
            	Rede Social:
              <input
                type="text"
                id="redeSocial"
                name="redeSocial"
                placeholder="Link da Rede Social Profissional"
                value="${funcionario.redeSocial }"
              />
            </label>

            <label class="label-input" for="senha">
            	Senha:
              <input
                type="password"
                id="senha"
                name="senha"
                placeholder="Senha"
                value="${funcionario.senha }"
              />
              <i
                class="bi bi-eye-slash icone-senha"
                id="icone-senha"
                onclick="mostrarSenha('senha')"
              ></i>
            </label>
          </div>
          	<label class="label-input" for="perfil">
            	Perfil profissional:
            <textarea name="perfil" id="perfil" placeholder="Perfil Profissional..." >${funcionario.perfil}</textarea>
          </label>

	
          <div class="button-group">
            <button type="submit" class="btn" id="botao" name="botao" value="Atualizar">Atualizar</button>
          </div>
        </form>
      </div>
    </section>

    <script src="./resources/js/meusDadosFuncionario.js"></script>
  </body>
</html>
