<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleLoginCadastroCliente.css"/>'>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perpétua Beleza e Estética - Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
	<div id="error-container"></div>
	<div id="success-container"></div>
	
	<c:if test="${not empty mensagemSucesso}">
		<script src="./resources/js/loginCadastroCliente.js"></script> 
    	<script>
        	displaySuccessMessage("${mensagemSucesso}");
    	</script>
	</c:if>
	
	<c:if test="${not empty mensagemErro}">
		<script src="./resources/js/loginCadastroCliente.js"></script> 
    	<script>
        	displayErrorMessage("${mensagemErro}");
    	</script>
	</c:if>
	
    <div class="container">
        <div class="content first-content">
            <div class="first-column">
                <h2 class="title title-primary">Bem Vindo de Volta!</h2>
                <p class="description description-primary">Caso não possua uma conta ainda</p>
                <p class="description description-primary">cadastre-se agora</p>
                <button id="signin" class="btn btn-primary">Cadastre-se</button>
            </div>    
            <div class="second-column">
                <h2 class="title title-second">Login</h2> 
                <form action="login" method="POST" onsubmit="return validaCamposLogin()" class="form form-singin">
                    <label class="label-input" for="">
                        <input type="email" id="email" name="email" placeholder="Email">
                    </label>
                    
                    <label class="label-input" for="senha">
                        <input type="password" id="senha" name="senha" placeholder="Senha">
                        <i class="bi bi-eye-slash" id="icone-senha" onclick="mostrarSenha('senha','icone-senha')"></i>
                    </label>
                     <input type="submit" class="btn btn-second" name="botao" id="botaoLogin" value="Entrar">
                    <!--<button class="btn btn-second">Entrar</button>  -->      
                </form>
            </div>
        </div>
        
        <div class="content second-content">
            <div class="first-column">
                <h2 class="title title-primary">Olá, amigo</h2>
                <p class="description description-primary">Se já possui uma conta,</p>
                <p class="description description-primary">então faça o login</p>
                <button id="signup" class="btn btn-primary">Login</button>
            </div>
            <div class="second-column">
                <h2 class="title title-second">Crie sua conta!</h2>
                <form action="cliente" method="POST" onsubmit="return validateForm()" class="form form-singup" >
                    <div class="inputs">
                        <label class="label-input" for="">
                            <input type="email" id="emailCadastro" name="email" placeholder="Email" value="${cliente.email}">
                        </label>
                    
                        <label class="label-input" for="">
                            <input type="text" id="nome" name="nome" placeholder="Nome" value="${cliente.nome}">
                        </label>
                    
                        <label class="label-input" for="">
                            <input type="text" id="telefone" name="telefone" placeholder="Número de telefone" value="${cliente.telefone}">
                        </label>
                    
                        <label class="label-input" for="">
                            <input type="text" id="cpf" name="cpf" placeholder="CPF" value="${cliente.cpf}">
                        </label>
                    
                        <label class="label-input" for="">
                            <input type="text" id="logradouro" name="logradouro" placeholder="Logradouro" value="${cliente.endereco.split(', ')[0]}">
                        </label>
                    
                        <label class="label-input" for="">
                            <input type="text" id="numero" name="numero" placeholder="Número" value="${cliente.endereco.split(', ')[1]}">
                        </label>
                    
                        <label class="label-input" for="senhaCadastro">
                            <input type="password" id="senhaCadastro" name="senha" placeholder="Senha" value="${cliente.senha}">
                            <i class="bi bi-eye-slash" id="icone-senha-cadastro" onclick="mostrarSenha('senhaCadastro', 'icone-senha-cadastro')"></i>
                        </label>
                        
                        <label class="label-input" for="confirmarSenha">
                            <input type="password" id="confirmarSenha" name="confirmarSenha" placeholder="Confirmar Senha">
                            <i class="bi bi-eye-slash" id="icone-senha-confirmar" onclick="mostrarSenha('confirmarSenha', 'icone-senha-confirmar')"></i>
                        </label>
                    </div>
	
                    <input type="submit" class="btn btn-second" name="botao" id="botao" value="Cadastre-se">
                </form>
            </div>
        </div>
    </div>

    <script src="./resources/js/loginCadastroCliente.js"></script> 
</body>
</html>
