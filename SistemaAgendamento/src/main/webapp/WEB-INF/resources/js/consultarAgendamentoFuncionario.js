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

const frases = [
	"Você é capaz de transformar cada desafio em aprendizado e crescimento.",
    "Sua dedicação ilumina o caminho para o sucesso de todos ao seu redor.",
    "Confie no seu talento e lembre-se: você faz a diferença aqui.",
    "Hoje é mais uma oportunidade para brilhar e espalhar gentileza.",
    "Cada pequeno gesto de cuidado seu inspira todos à sua volta.",
    "Seu empenho transforma dias comuns em dias especiais.",
    "Seus esforços são valorizados e cada detalhe faz toda a diferença.",
    "A sua presença torna o ambiente mais leve e cheio de vida.",
    "Lembre-se: você é única e cada conquista sua é merecida.",
    "Continue sendo essa luz que contagia e inspira quem está por perto.",
    "Acredite no poder que você tem de fazer a diferença.",
    "Seu sorriso e dedicação tornam o ambiente melhor a cada dia.",
    "A cada desafio superado, você se torna ainda mais forte.",
    "Agradecemos pelo cuidado e amor que você coloca no seu trabalho.",
    "Seu talento brilha e inspira todos ao redor!",
    "Você é a peça fundamental para o sucesso de nossa equipe.",
    "Que seu dia seja leve e repleto de conquistas.",
    "Obrigada por tornar tudo mais especial com seu trabalho.",
    "O seu esforço e dedicação não passam despercebidos.",
    "Que seu talento continue abrindo caminhos incríveis para você."
];

const frasesElementos = document.querySelectorAll('.titulo-sub p');

function escolherFraseAleatoria() {
    const indiceAleatorio = Math.floor(Math.random() * frases.length);
    return frases[indiceAleatorio];
}

function trocarFrases() {
    frasesElementos.forEach(fraseElemento => {
        fraseElemento.classList.add('fade-out');

        setTimeout(() => {
            fraseElemento.textContent = escolherFraseAleatoria();
            
            fraseElemento.classList.remove('fade-out');
        }, 500); 
    });
}

document.addEventListener("DOMContentLoaded", () => {
    frasesElementos.forEach(fraseElemento => {
        fraseElemento.textContent = escolherFraseAleatoria();
    });

    setInterval(trocarFrases, 20000);
});