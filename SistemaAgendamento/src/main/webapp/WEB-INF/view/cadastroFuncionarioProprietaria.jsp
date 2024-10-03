<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css"
	href='<c:url value = "./resources/css/styleCadastroProdutoProprietaria.css"/>'>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<title>Perpétua Beleza e Estética - Funcionários</title>
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
					<li class="nav-link"><a href="#"> <i
							class='bx bx-home-alt icon'></i> <span class="texto nav-texto">Dashboard</span>
					</a></li>
					<li class="nav-link"><a href="#"> <i
							class='bx bx-bar-chart-alt-2 icon'></i> <span
							class="texto nav-texto">Revenue</span>
					</a></li>
					<li class="nav-link"><a href="#"> <i
							class='bx bx-bell icon'></i> <span class="texto nav-texto">Norifications</span>
					</a></li>
					<li class="nav-link"><a href="#"> <i
							class='bx bx-pie-chart-alt icon'></i> <span
							class="texto nav-texto">Analytics</span>
					</a></li>
					<li class="nav-link"><a href="#"> <i
							class='bx bx-heart icon'></i> <span class="texto nav-texto">Likes</span>
					</a></li>
					<li class="nav-link"><a href="#"> <i
							class='bx bx-wallet icon'></i> <span class="texto nav-texto">Wallets</span>
					</a></li>
				</ul>
			</div>

		</div>

		<div class="baixo">
			<li class=""><a href="#"> <i class='bx bx-log-out icon'></i>
					<span class="texto nav-texto">Logout</span>
			</a></li>
		</div>
	</nav>

	<div id="error-container"></div>
	<div id="success-container"></div>
	<section class="content">


		<c:if test="${not empty mensagemSucesso}">
			<script src="./resources/js/cadastroFuncionarioProprietaria.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/cadastroFuncionarioProprietaria.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if>

		<div class="header">
			<h1>Funcionários</h1>
		</div>

		<div class="form-container">
			<form action="funcionario" method="post"
				onsubmit="return validaCampos()" class="form">
				<div class="input-buscar">
					<div class="label-input">
						<label for="nome">Nome:</label> <input type="text" id="nome"
							name="nome" placeholder="Nome do funcionário"
							value="${funcionario.nome }">
					</div>
				</div>

				<div class="input-buscar">
					<div class="label-input">
						<label for="email">Email:</label> <input type="text" id="email"
							name="email" placeholder="Email do funcionário"
							value="${funcionario.email }">
					</div>
				</div>

				<div class="input-buscar">
					<div class="label-input">
						<label for="senha">Senha:</label> <input type="password"
							id="senha" name="senha" placeholder="Senha do funcionário"
							value="${funcionario.senha}">
					</div>
				</div>

				<div class="input-buscar">
					<div class="label-input">
						<label for="telefone">Telefone:</label> <input type="text"
							id="telefone" name="telefone" pattern="[0-9]*" title="Por favor, digite apenas números."
							placeholder="Telefone do funcionário"
							value="${funcionario.telefone }">
					</div>
				</div>

				<div class="input-buscar">
					<div class="label-input">
						<label for="cpf">CPF:</label> <input type="text" id="cpf"
							name="cpf" placeholder="CPF do funcionário"
							value="${funcionario.cpf }">
					</div>
				</div>

				<div class="input-buscar">
					<div class="label-input">
						<label for="acesso">Acesso:</label> <select id="acesso"
							name="acesso">
							<option value="1" ${funcionario.nivelAcesso == '1' ? 'selected' : ''}>Funcionario</option>
							<option value="2" ${funcionario.nivelAcesso == '2' ? 'selected' : ''}>Proprietaria</option>
						</select>
					</div>
				</div>

				<input type="hidden" id="id" name="id" value="${funcionario.id}" />
				<input type="hidden" name="pagina" id="pagina" value="proprietaria"/>

				<div class="button-group">
					<input type="submit" class="btn" id="cadastrar" name="botao"
						value="Cadastrar"> <input type="submit" class="btn"
						id="atualizar" name="botao" value="Atualizar">
				</div>
			</form>
		</div>

		<div class="tabela-container">
			<c:if test="${not empty funcionarios }">
				<table>
					<thead>
						<tr>
							<th>Nome</th>
							<th>Email</th>
							<th>Telefone</th>
							<th>CPF</th>
							<th>Acesso</th>
							<th>Editar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="f" items="${funcionarios }">
							<tr>
								<td><c:out value="${f.nome }" /></td>
								<td><c:out value="${f.email }" /></td>
								<td><c:out value="${f.telefone }" /></td>
								<td><c:out value="${f.cpf }" /></td>
								<td>${f.nivelAcesso == 1 ? 'Funcionario' : f.nivelAcesso == 2 ? 'Proprietaria' : 'Valor desconhecido'}</td>
								<form action="funcionario" method="post">
									<input type="hidden" id="id" name="id" value="${f.id}" />
									<td>
										<button type="submit" class="btn" id="botao" name="botao"
											value="Editar">
											<i class='bx bxs-edit'></i>
										</button>
									</td>
								</form>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</section>


	<script src="./resources/js/cadastroFuncionarioProprietaria.js"></script>
</body>

</html>