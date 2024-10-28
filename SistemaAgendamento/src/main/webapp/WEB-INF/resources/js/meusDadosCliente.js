const body = document.querySelector("body"),
    sidebar = document.querySelector(".sidebar"),
    toggle = document.querySelector(".imagem-texto");

toggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});

function validateForm() {
	var form = document.querySelector('.form-singup'); // Seleciona o formulário diretamente
	var email = form["email"].value;
	var senha = form["senha"].value;
	var cpf = form["cpf"].value;
	var senha = form["senha"].value;
	var telefone = form["telefone"].value;
	var logradouro = form["logradouro"].value;
	var numero = form["numero"].value;
	var nome = form["nome"].value;

	var errorMessage = "";

	if (!email) {
		errorMessage += "O campo 'Email' é obrigatório.<br>";
	}
	if (!senha) {
		errorMessage += "O campo 'Senha' é obrigatório.<br>";
	}
	if (!telefone) {
		errorMessage += "O campo 'Telefone' é obrigatório.<br>";
	}
	if (!logradouro) {
		errorMessage += "O campo 'Logradouro' é obrigatório.<br>";
	}
	if (!numero) {
		errorMessage += "O campo 'Numero' é obrigatório.<br>";
	}
	if (!nome) {
		errorMessage += "O campo 'Nome' é obrigatório.<br>";
	}
	if (!cpf || cpf.length != 11) {
		errorMessage += "O campo 'CPF' deve ter 11 dígitos.<br>";
	}

	// Limpa mensagens de erro anteriores
	var errorContainer = document.getElementById("error-container");
	errorContainer.innerHTML = ""; // Limpa o conteúdo

	if (errorMessage) {
		// Cria a div para a mensagem de erro
		var errorDiv = document.createElement('div');
		errorDiv.className = 'error-message';
		errorDiv.innerHTML = '<strong>Erro!</strong><br> ' + errorMessage;

		errorContainer.appendChild(errorDiv); // Adiciona a mensagem de erro ao container

		// Remove a mensagem após 5 segundos
		setTimeout(function() {
			errorContainer.removeChild(errorDiv);
		}, 5000);

		return false;  // Evita o envio do formulário
	}
	return true;
}

function displaySuccessMessage(message) {
	var successContainer = document.getElementById("success-container");
	successContainer.innerHTML = ""; // Limpa o conteúdo anterior

	// Cria a div para a mensagem de sucesso
	var successDiv = document.createElement('div');
	successDiv.className = 'success-message';
	successDiv.innerHTML = '<strong>Sucesso!</strong><br> ' + message;

	successContainer.appendChild(successDiv); // Adiciona a mensagem ao container

	// Define um timeout para remover a mensagem após 5 segundos
	setTimeout(function() {
		successContainer.innerHTML = ""; // Limpa a mensagem após o tempo definido
	}, 5000);
}

function displayErrorMessage(message) {
	var errorContainer = document.getElementById("error-container");
	errorContainer.innerHTML = ""; // Limpa o conteúdo anterior

	var errorDiv = document.createElement('div');
	errorDiv.className = 'error-message';
	errorDiv.innerHTML = '<strong>Erro!</strong><br> ' + message;

	errorContainer.appendChild(errorDiv); // Adiciona a mensagem de erro ao container

	// Remove a mensagem após 5 segundos
	setTimeout(function() {
		errorContainer.removeChild(errorDiv);
	}, 5000);
}

document.addEventListener('DOMContentLoaded', function() {
    const botaoSelecionarDiaCompleto = document.getElementById('botaoSelecionarDiaCompleto');
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');

    botaoSelecionarDiaCompleto.addEventListener('click', function(event) {
        event.preventDefault(); 
        checkboxes.forEach(function(checkbox) {
            checkbox.checked = true;
        });
    });
});

function mostrarSenha(inputId) {
    var inputPass = document.getElementById(inputId)
    var btnShowPass = document.getElementById('icone-senha')

    if(inputPass.type === 'password'){
        inputPass.setAttribute('type','text')
        btnShowPass.classList.replace('bi-eye-slash','bi-eye')
    } else {
        inputPass.setAttribute('type','password')
        btnShowPass.classList.replace('bi-eye','bi-eye-slash')
    }
}
