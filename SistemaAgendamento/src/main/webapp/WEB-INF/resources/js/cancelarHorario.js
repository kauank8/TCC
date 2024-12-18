const body = document.querySelector("body"),
  sidebar = document.querySelector(".sidebar"),
  toggle = document.querySelector(".imagem-texto"),
  contentSection = document.querySelector(".content");

toggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});

contentSection.addEventListener("click", () => {
  if (!sidebar.classList.contains("close")) {
    sidebar.classList.add("close");
  }
});

function validaCampos(){
	var form = document.querySelector('.form');
	var data = form["data"].value.trim();
	var justificativa = form["justificativa"].value.trim();

	var errorMessage = "";
	var today = new Date().toISOString().split('T')[0];

	if (!data) {
		errorMessage += "É necessário selecionar uma data.<br>";
	}else if (data < today) {
        errorMessage += "A data selecionada não pode ser anterior à data atual.<br>";
    }
    
    if(!justificativa){
		errorMessage += "É necessário preencher a justificativa.<br>";
	}
	
	// Verifica se ao menos um checkbox de horários foi selecionado
    var checkboxes = document.querySelectorAll('input[name="horarios"]:checked'); // Seleciona os checkboxes marcados
    if (checkboxes.length === 0) {
        errorMessage += "É necessário selecionar pelo menos um horário.<br>";
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

