const body = document.querySelector("body"),
  sidebar = document.querySelector(".sidebar"),
  toggle = document.querySelector(".imagem-texto");

toggle.addEventListener("click", () => {
  sidebar.classList.toggle("close");
});

function validaCampos() {
  var form = document.querySelector(".form");
  var data = form["data"].value.trim();
  var funcionario = form["funcionario"].value.trim();
  var horarioSelecionado = form.querySelector('input[name="horarioSelecionado"]:checked'); // Verifica se algum horário está marcado
  
  var errorMessage = "";

  if (!data) {
    errorMessage += "O campo 'Data' é obrigatório.<br>";
  }
   if (!funcionario || funcionario === "") { // Verifica se "funcionario" está vazio ou é a opção desabilitada
    errorMessage += "É necessario selecionar um profissional para confirmar o agendamento.<br>";
  }
   if (!horarioSelecionado) {
    errorMessage += "É necessário selecionar um horário para confirmar o agendamento.<br>";
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

// document.addEventListener('DOMContentLoaded', function() {
//     const botaoSelecionarDiaCompleto = document.getElementById('botaoSelecionarDiaCompleto');
//     const checkboxes = document.querySelectorAll('input[type="checkbox"]');

//     botaoSelecionarDiaCompleto.addEventListener('click', function(event) {
//         event.preventDefault();
//         checkboxes.forEach(function(checkbox) {
//             checkbox.checked = true;
//         });
//     });
// });

function mostrarSenha(inputId) {
  var inputPass = document.getElementById(inputId);
  var btnShowPass = document.getElementById("icone-senha");

  if (inputPass.type === "password") {
    inputPass.setAttribute("type", "text");
    btnShowPass.classList.replace("bi-eye-slash", "bi-eye");
  } else {
    inputPass.setAttribute("type", "password");
    btnShowPass.classList.replace("bi-eye", "bi-eye-slash");
  }
}

document
  .querySelectorAll('.service-item input[type="checkbox"]')
  .forEach((checkbox) => {
    checkbox.addEventListener("change", function () {
      const serviceItem = this.closest(".service-item");
      if (this.checked) {
        serviceItem.style.backgroundColor = "#ffb6c1cb";
        serviceItem.style.border = "2px solid #707070";
      } else {
        serviceItem.style.backgroundColor = ""; // Volta ao estado original
      }
    });
  });

// document.querySelectorAll('.service-item').forEach(serviceItem => {
//     const checkbox = serviceItem.querySelector('input[type="checkbox"]');

//     serviceItem.addEventListener('click', function() {
//         checkbox.checked = !checkbox.checked; // Alterna o estado do checkbox
//         const event = new Event('change'); // Cria um novo evento de mudança
//         checkbox.dispatchEvent(event); // Dispara o evento de mudança para executar a lógica existente
//     });

//     checkbox.addEventListener('change', function() {
//         const serviceItem = this.closest('.service-item');
//         if (this.checked) {
//             serviceItem.style.backgroundColor = '#ffb6c1cb';
//             serviceItem.style.border = '2px solid #707070';
//         } else {
//             serviceItem.style.backgroundColor = ''; // Volta ao estado original
//         }
//     });
// });
// document.querySelectorAll('.service-item').forEach(serviceItem => {
//     const checkbox = serviceItem.querySelector('input[type="checkbox"]');

//     serviceItem.addEventListener('click', function(event) {
//         // Verifica se o clique não foi no checkbox
//         if (event.target !== checkbox) {
//             checkbox.checked = !checkbox.checked; // Alterna o estado do checkbox
//             const changeEvent = new Event('change'); // Cria um novo evento de mudança
//             checkbox.dispatchEvent(changeEvent); // Dispara o evento de mudança para executar a lógica existente
//         }
//     });

//     checkbox.addEventListener('change', function() {
//         const serviceItem = this.closest('.service-item');
//         if (this.checked) {
//             serviceItem.style.backgroundColor = '#ffb6c1cb';
//             serviceItem.style.border = '2px solid #707070';
//         } else {
//             serviceItem.style.backgroundColor = ''; // Volta ao estado original
//             serviceItem.style.border = ''; // Volta a borda ao estado original
//         }
//     });
// });

document.addEventListener("DOMContentLoaded", function () {
  var swiper = new Swiper(".horarios", {
    slidesPerView: 4,
    spaceBetween: 12,
    navigation: {
      nextEl: ".bxs-caret-right-circle",
      prevEl: ".bxs-caret-left-circle",
    },
    loop: false,
  });
});

document.addEventListener("DOMContentLoaded", function () {
    var swiper = new Swiper(".horarios", {
        slidesPerView: 4, // Ajuste conforme necessário
        spaceBetween: 20, // Ajuste conforme necessário
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        loop: true, // Habilita o loop
    });

    // Seleciona todos os horários
    const horarios = document.querySelectorAll('.swiper-slide.horario');

    // Adiciona o evento de clique a cada horário
    horarios.forEach(horario => {
        horario.addEventListener('click', function() {
            // Remove a classe 'selected' de todos os horários
            horarios.forEach(h => {
                h.classList.remove('selected');
                // Desmarca todos os inputs de rádio
                const radio = h.querySelector('input[type="radio"]');
                if (radio) {
                    radio.checked = false;
                }
            });

            // Adiciona a classe 'selected' ao horário clicado
            this.classList.add('selected');
            
            // Marca o input de rádio correspondente
            const radioSelecionado = this.querySelector('input[type="radio"]');
            if (radioSelecionado) {
                radioSelecionado.checked = true;
            }
        });
    });
});
