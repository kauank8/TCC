<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cadastro de Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <h3>Cadastro de Cliente</h3>
    
    <!-- Exibindo Mensagens de Erro -->
    <c:if test="${not empty erro}">
        <div style="color: red;">
            <p>${erro}</p>
        </div>
    </c:if>

    <!-- Exibindo Mensagem de Sucesso -->
    <c:if test="${not empty mensagem}">
        <div style="color: green;">
            <p>${mensagem}</p>
        </div>
    </c:if>

    <!-- Formulário de Cliente -->
    <form action="cliente" method="POST" onsubmit="return validateForm()">
        Id: <input type="text" name="id"><br>
        Email: <input type="text" name="email"><br>
        Senha: <input type="password" name="senha"><br>
        Nível de Acesso: <input type="text" name="nivelAcesso"><br>
        Nome: <input type="text" name="nome"><br>
        CPF: <input type="text" name="cpf"><br>
        Telefone: <input type="text" name="telefone"><br>
        Endereço: <input type="text" name="endereco"><br>
        <input id="botao" name="cmd" type="submit" class="btn btn-success" value="Cadastrar">
         <input id="botao" name="cmd" type="submit" class="btn btn-success" value="Atualizar">
    </form>
    
    <!-- Formulário de Funcionário -->
    <form action="funcionario" method="POST" onsubmit="return validateFuncionarioForm()">
        <input type="hidden" name="id" value="${funcionario.id}"> <!-- Campo oculto para ID -->
        id: <input type="text" name="id"><br>
        Email: <input type="text" name="email" value="${funcionario.email}"><br>
        Senha: <input type="password" name="senha" value="${funcionario.senha}"><br>
        Nível de Acesso: <input type="text" name="nivelAcesso" value="${funcionario.nivelAcesso}"><br>
        Nome: <input type="text" name="nome" value="${funcionario.nome}"><br>
        CPF: <input type="text" name="cpf" value="${funcionario.cpf}"><br>
        Telefone: <input type="text" name="telefone" value="${funcionario.telefone}"><br>
        Perfil: <input type="text" name="perfil" value="${funcionario.perfil}"><br>
        Rede Social: <input type="text" name="redeSocial" value="${funcionario.redeSocial}"><br>
       <input id="botao" name="cmd" type="submit" class="btn btn-success" value="Cadastrar">
        <input id="botao" name="cmd" type="submit" class="btn btn-success" value="Atualizar">
    </form>
    

    <script>
    function validateForm() {
        var email = document.forms[0]["email"].value;
        var senha = document.forms[0]["senha"].value;
        var cpf = document.forms[0]["cpf"].value;

        var errorMessage = "";

        if (!email) {
            errorMessage += "O campo 'Email' é obrigatório.\n";
        }
        if (!senha) {
            errorMessage += "O campo 'Senha' é obrigatório.\n";
        }
        if (!cpf || cpf.length != 11) {
            errorMessage += "O campo 'CPF' deve ter 11 dígitos.\n";
        }

        if (errorMessage) {
            // Usando alertas do Bootstrap ao invés de alert nativo
            var alertDiv = document.createElement('div');
            alertDiv.className = 'alert alert-danger alert-dismissible fade show';
            alertDiv.innerHTML = '<strong>Erro!</strong> ' + errorMessage.replace(/\n/g, '<br>') +
                                 '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>';
            document.body.prepend(alertDiv);
            return false;  // Evita o envio do formulário
        }
        return true;  // Permite o envio do formulário
    }
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>