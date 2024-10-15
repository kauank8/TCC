<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleCadastrarServicoProprietaria.css"/>'>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <title>Perpétua Beleza e Estética - Serviços</title>
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
			<script src="./resources/js/cadastrarServicoProprietaria.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/cadastrarServicoProprietaria.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if>

        <div class="header">
            <h1>Serviço</h1>
        </div>

        <div class="form-container">
            <form action="servico" method="post" onsubmit="return validaCampos()" class="form">
                <div class="input-buscar">
                    <div class="label-input">
                        <label for="titulo">Título:</label>
                        <input type="text" id="titulo" name="titulo" placeholder="Insira o título do serviço" value="${servico.titulo }">
                    </div>
                </div>

                <div class="input-group">
                    <div class="duracao-preco-input">
                        <label for="duracao">Duração:</label>
                        <input type="text" id="duracao" name="duracao" placeholder="Minutos" value="${servico.duracao }"
                        pattern="[0-9]*" title="Por favor, digite apenas números." >
                    </div>
                    <div class="duracao-preco-input">
                        <label for="preco">Preço:</label>
                        <input type="text" id="preco" name="preco" placeholder="Reais" value="${servico.preco }"
                        pattern="[0-9.]*" title="Por favor, digite apenas números.">
                    </div>
                </div>
                <label for="status">Selecione o status:</label>
                <select id="status" name="status">
                    <option value="Disponivel" ${servico.status== 'Disponivel' ? 'selected' : ''}>Disponível</option>
                    <option value="Indisponivel" ${servico.status== 'Indisponivel' ? 'selected' : ''}>Indisponível</option>
                </select>

				<c:if test="${not empty produtos }">
	                <table class="servico-selecionar">
	                   <thead>
							<tr>
								<th>Selecionar</th>
								<th>Nome</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="p" items="${produtos}">
						<tr>
		           			<td>
			                   <input type="checkbox" name="produtos" value="${p.id}"
			                   <c:if test="${not empty servico.produtos && servico.produtos.contains(p)}">checked</c:if>
			                    >
		                    </td>
		                    <td>${p.nome }</td>
            			</tr>
						</c:forEach>
						</tbody>
	                </table>
				</c:if>
				
				<input type="hidden" id="id" name="id" value="${servico.id}" />
                <div class="button-group">
                    <button type="submit" class="btn" id="botao" name="botao" value="Cadastrar">Cadastrar</button>
                    <button type="submit" class="btn" id="botao" name="botao" value="Atualizar">Atualizar</button>
                </div>
            </form>
        </div>

        <div class="tabela-container">
        	<c:if test="${not empty servicos }">
	            <table>
	                <thead>
	                    <tr>
	                        <th>Título</th>
	                        <th>Duração</th>
	                        <th>Preço</th>
	                        <th>Status</th>
	                        <th>Editar</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<c:forEach var="s" items="${servicos }">
		                    <tr>
		                        <td><c:out value="${s.titulo }"/></td>
		                        <td><c:out value="${s.duracao }"/></td>
		                        <td><c:out value="${s.preco }"/></td>
		                        <td><c:out value="${s.status }"/></td>
		                        <form action="servico" method="post">
		                            <input type="hidden" id="id" name="id" value="${s.id }" />
		                            <input type="hidden" id="duracao" name="duracao" value="${s.duracao }" />
		                            <input type="hidden" id="preco" name="preco" value="${s.preco }" />
		                            <td>
		                                <button type="submit" class="btn" id="botao" name="botao" value="Editar">
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

    <script src="./resources/js/cadastrarServicoProprietaria.js"></script>
</body>

</html>