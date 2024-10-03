const body = document.querySelector("body"),
    sidebar = document.querySelector(".sidebar"),
    toggle = document.querySelector(".imagem-texto");

toggle.addEventListener("click", () => {
    sidebar.classList.toggle("close");
});

function validaCampos(){
	var form = document.querySelector('.form');
	var email = form["email"].value.trim();
	var senha = form["senha"].value.trim();
	var cpf = form["cpf"].value.trim();
	var senha = form["senha"].value.trim();
	var telefone = form["telefone"].value.trim();
	var nome = form["nome"].value.trim();

	var errorMessage = "";

	if (!email) {
		errorMessage += "O campo 'Email' é obrigatório.<br>";
	}
	if (!senha) {
		errorMessage += "O campo 'Senha' é obrigatório.<br>";
	}
	if (!telefone || telefone.length != 11) {
		errorMessage += "O campo 'Telefone' é obrigatório e deve possuir 11 digitos.<br>";
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

function mostrarSenha(inputId, iconId) {
	var inputPass = document.getElementById(inputId);
	var btnShowPass = document.getElementById(iconId);

	btnShowPass.onmousedown = function() {
		inputPass.setAttribute('type', 'text');
		btnShowPass.classList.replace('bi-eye-slash', 'bi-eye');
	};

	btnShowPass.onmouseup = function() {
		inputPass.setAttribute('type', 'password');
		btnShowPass.classList.replace('bi-eye', 'bi-eye-slash');
	};

	// Para garantir que a senha volte ao normal se o mouse sair do ícone
	btnShowPass.onmouseleave = function() {
		inputPass.setAttribute('type', 'password');
		btnShowPass.classList.replace('bi-eye', 'bi-eye-slash');
	};
}