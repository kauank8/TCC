var btnSignin = document.querySelector("#signin");
var btnSignup = document.querySelector("#signup");
var body = document.querySelector("body");

btnSignin.addEventListener("click", function() {
	body.className = "sign-in-js";
});

btnSignup.addEventListener("click", function() {
	body.className = "sign-up-js";
});

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
function validateForm() {
	var form = document.querySelector('.form-singup'); // Seleciona o formulário diretamente
	var email = form["email"].value;
	var senha = form["senha"].value;
	var cpf = form["cpf"].value;
	var senha = form["senha"].value;
	var telefone = form["telefone"].value;
	var confirmarSenha = form["confirmarSenha"].value;
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
	if (!confirmarSenha) {
		errorMessage += "O campo 'Confirmar Senha' é obrigatório.<br>";
	}
	if (senha !== confirmarSenha) { // Verifica se as senhas são iguais
		errorMessage += "As senhas não coincidem.<br>";
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

function validaCamposLogin(){
	var form = document.querySelector('.form-singin');
	var email = form["email"].value;
	var senha = form["senha"].value;
	var errorMessage = "";
	if (!email) {
		errorMessage += "O campo 'Email' é obrigatório.<br>";
	}
	if (!senha) {
		errorMessage += "O campo 'Senha' é obrigatório.<br>";
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