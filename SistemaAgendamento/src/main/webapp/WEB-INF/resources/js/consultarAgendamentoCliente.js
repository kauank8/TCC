const body = document.querySelector("body"),
  sidebar = document.querySelector(".sidebar"),
  toggle = document.querySelector(".imagem-texto"),
  contentSection = document.querySelector(".form");

toggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});

contentSection.addEventListener("click", () => {
  if (!sidebar.classList.contains("close")) {
    sidebar.classList.add("close");
  }
});

function validaCampos() {
	var form = document.querySelector('.form');
	var data = form["data"].value.trim();

	var errorMessage = "";
	var today = new Date().toISOString().split('T')[0];

	if (!data) {
		errorMessage += "É necessário selecionar uma data.<br>";
	}else if (data < today) {
        errorMessage += "A data selecionada não pode ser anterior à data atual.<br>";
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

// Função para abrir o modal
function abrirModal(agendamentoId) {
    // Esconde todos os modais
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => modal.style.display = 'none');
  
    // Exibe o modal correspondente e garante que ele será centralizado
    const modal = document.getElementById('modal-' + agendamentoId);
    if (modal) {
        modal.style.display = 'flex'; // Exibe o modal com display flex
    }
}

// Função para fechar o modal
function fecharModal(event) {
    // Verifica se o clique foi no fundo ou no ícone de fechar
    const modal = event.target;

    // Se o clique foi no fundo ou no ícone de fechar, fecha o modal
    if (modal.classList.contains('modal') || modal.classList.contains('close-btn')) {
        // Encontre o modal e altere o display para 'none' para fechá-lo
        modal.closest('.modal').style.display = 'none';
    }
}

