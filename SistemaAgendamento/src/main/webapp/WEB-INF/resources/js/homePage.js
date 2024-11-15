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
  var form = document.querySelector(".form-singup"); // Seleciona o formulário diretamente
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
    var errorDiv = document.createElement("div");
    errorDiv.className = "error-message";
    errorDiv.innerHTML = "<strong>Erro!</strong><br> " + errorMessage;

    errorContainer.appendChild(errorDiv); // Adiciona a mensagem de erro ao container

    // Remove a mensagem após 5 segundos
    setTimeout(function () {
      errorContainer.removeChild(errorDiv);
    }, 5000);

    return false; // Evita o envio do formulário
  }
  return true;
}

document.addEventListener("DOMContentLoaded", function () {
  var swiper = new Swiper(".mySwiper", {
    effect: "cube",
    grabCursor: true,
    cubeEffect: {
      shadow: true,
      slideShadows: true,
      shadowOffset: 20,
      shadowScale: 0.94,
    },
    loop: true,
    pagination: {
      el: ".swiper-pagination",
    },
    autoplay: {
      delay: 6000, // tempo entre as transições (em milissegundos)
      disableOnInteraction: false, // permite que o autoplay continue mesmo após a interação
    },
    speed: 1500,
  });
});

document.addEventListener("DOMContentLoaded", function () {
  var swiperMeio = new Swiper(".mySwiper-meio", {
    spaceBetween: 30,
    effect: "fade",
    pagination: {
      el: ".swiper-pagination",
      clickable: true,
    },
	autoplay: {
		delay: 2000, // tempo entre as transições (em milissegundos)
		disableOnInteraction: false, // permite que o autoplay continue mesmo após a interação
	  },
	  speed: 1500,
  });
});

function displaySuccessMessage(message) {
  var successContainer = document.getElementById("success-container");
  successContainer.innerHTML = ""; // Limpa o conteúdo anterior

  // Cria a div para a mensagem de sucesso
  var successDiv = document.createElement("div");
  successDiv.className = "success-message";
  successDiv.innerHTML = "<strong>Sucesso!</strong><br> " + message;

  successContainer.appendChild(successDiv); // Adiciona a mensagem ao container

  // Define um timeout para remover a mensagem após 5 segundos
  setTimeout(function () {
    successContainer.innerHTML = ""; // Limpa a mensagem após o tempo definido
  }, 5000);
}

function displayErrorMessage(message) {
  var errorContainer = document.getElementById("error-container");
  errorContainer.innerHTML = ""; // Limpa o conteúdo anterior

  var errorDiv = document.createElement("div");
  errorDiv.className = "error-message";
  errorDiv.innerHTML = "<strong>Erro!</strong><br> " + message;

  errorContainer.appendChild(errorDiv); // Adiciona a mensagem de erro ao container

  // Remove a mensagem após 5 segundos
  setTimeout(function () {
    errorContainer.removeChild(errorDiv);
  }, 5000);
}
