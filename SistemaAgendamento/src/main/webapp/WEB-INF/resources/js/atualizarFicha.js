const body = document.querySelector("body"),
  sidebar = document.querySelector(".sidebar"),
  toggle = document.querySelector(".imagem-texto");

toggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});

// function validateForm() {
//   var form = document.querySelector(".form-pesquisar"); // Seleciona o formulário diretamente
//   var nome = form["nome-pesquisa"].value;
//   var cpf = form["cpf-pesquisa"].value;

//   var errorMessage = "";

//   if (!nome && !cpf) {
//     errorMessage += "O campo 'Nome' ou o campo 'CPF' são necessarios para a pesquisa.<br>";
//   }

//   if (!nome && (cpf && cpf.length != 11)) {
//      errorMessage += "O campo 'CPF' deve ter 11 dígitos.<br>";
//   }

//   if(nome && cpf){
// 	errorMessage += "Por favor, preencha apenas o campo 'Nome' ou o campo 'CPF' para realizar a pesquisa.<br>";
//   }

//   // Limpa mensagens de erro anteriores
//   var errorContainer = document.getElementById("error-container");
//   errorContainer.innerHTML = ""; // Limpa o conteúdo

//   if (errorMessage) {
//     // Cria a div para a mensagem de erro
//     var errorDiv = document.createElement("div");
//     errorDiv.className = "error-message";
//     errorDiv.innerHTML = "<strong>Erro!</strong><br> " + errorMessage;

//     errorContainer.appendChild(errorDiv); // Adiciona a mensagem de erro ao container

//     // Remove a mensagem após 5 segundos
//     setTimeout(function () {
//       errorContainer.removeChild(errorDiv);
//     }, 5000);

//     return false; // Evita o envio do formulário
//   }
//   return true;
// }

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

const checkboxProdutos = document.querySelectorAll('.checkbox-produto');

checkboxProdutos.forEach(produto => {
    produto.addEventListener('click', (event) => {
        if (event.target.tagName !== 'INPUT') {
            const checkbox = produto.querySelector('input[type="checkbox"]');
            checkbox.checked = !checkbox.checked; 
        }
    });
});