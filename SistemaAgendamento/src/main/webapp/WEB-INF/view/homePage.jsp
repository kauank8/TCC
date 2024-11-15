<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" type="text/css" href='<c:url value = "./resources/css/styleHomePage.css"/>'>
    <link
      href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
      rel="stylesheet"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />
    <title>Perpétua Beleza e Estética - Home Page</title>
  </head>
  <body>
    <nav class="sidebar close">
      <header>
        <div class="imagem-texto">
          <i class="bx bx-menu sandwich"></i>

          <div class="texto header-texto">
            <span class="salao">Perpétua Beleza e Estética</span>
          </div>
        </div>
      </header>

      <div class="menu-bar">
        <div class="menu">
          <ul class="menu-links">
            <li class="nav-link">
              <a href="homePage">
                <i class="bx bx-home-alt icon"></i>
                <span class="texto nav-texto">Home</span>
              </a>
            </li>
           <li class="nav-link">
              <a href="consultarServicoCliente">
                <i class='bx bx-cut icon'></i>
                <span class="texto nav-texto">Serviços</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="consultarAgendamentoCliente">
                <i class='bx bxs-calendar icon'></i>
                <span class="texto nav-texto">Meus Agendamentos</span>
              </a>
            </li>
            <li class="nav-link">
              <a href="meusDadosCliente">
                <i class='bx bx-user-circle icon'></i>
                <span class="texto nav-texto">Meus Dados</span>
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div class="baixo">
        <li class="">
         <form action="login" method="POST" >
          <button type="submit"  id="botao" name="botao" value="Logout">
            <i class="bx bx-log-out icon"></i>
            <span class="texto nav-texto">Logout</span>
          </button>
          </form>
        </li>
      </div>
    </nav>

    <div id="error-container"></div>
    <div id="success-container"></div>

    <section class="content">
      <c:if test="${not empty mensagemSucesso}">
			<script src="./resources/js/homePage.js"></script>
			<script>
				displaySuccessMessage("${mensagemSucesso}");
			</script>
		</c:if>

		<c:if test="${not empty mensagemErro}">
			<script src="./resources/js/homePage.js"></script>
			<script>
				displayErrorMessage("${mensagemErro}");
			</script>
		</c:if>

      <div class="topo">
        <div class="infos-topo">
          <h1>Perpétua Beleza e Estética</h1>
          <p>
            Nossa missão é oferecer a cada cliente um atendimento excepcional,
            com produtos de qualidade e profissionais apaixonados pelo que
            fazem. Aqui, você encontra um espaço dedicado ao seu bem-estar e à
            sua melhor versão.
          </p>
          <a href="consultarAgendamentoCliente" class="btn">Agende seu horário</a>
        </div>

        <div class="carrossel-topo">
          <div class="swiper mySwiper">
            <div class="swiper-wrapper">
              <div class="swiper-slide">
                <img
                  src="./resources/images/imagemProvisoria.jpg"
                />
              </div>
              <div class="swiper-slide">
                <img
                  src="./resources/images/imagemProvisoria.jpg"
                />
              </div>
              <div class="swiper-slide">
                <img
                  src="./resources/images/imagemProvisoria.jpg"
                />
              </div>
              <div class="swiper-slide">
                <img
                  src="./resources/images/imagemProvisoria.jpg"
                />
              </div>
            </div>
            <div class="swiper-pagination"></div>
          </div>
        </div>
      </div>

      <div class="meio">
        <h2>Conheça o nosso trabalho!</h2>

        <div class="carrossel-meio">
          <div class="swiper-meio mySwiper-meio">
            <div class="swiper-wrapper">
              <div class="swiper-slide swiper-slide-meio">
                <img src="https://swiperjs.com/demos/images/nature-1.jpg" />
              </div>
              <div class="swiper-slide swiper-slide-meio">
                <img src="https://swiperjs.com/demos/images/nature-2.jpg" />
              </div>
              <div class="swiper-slide swiper-slide-meio">
                <img src="https://swiperjs.com/demos/images/nature-3.jpg" />
              </div>
              <div class="swiper-slide swiper-slide-meio">
                <img src="https://swiperjs.com/demos/images/nature-4.jpg" />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="base">
        <div class="infos-base">
          <div class="contato-info">
            <h2>Contato & Localização</h2>
            <p><strong>Endereço:</strong> Av. Principal, 123 - Bairro, Cidade, Estado</p>
            <p><strong>Telefone:</strong> (11) 1234-5678</p>
            <p><strong>E-mail:</strong> contato@salaoexemplo.com</p>
            <p><strong>Horário de Funcionamento:</strong></p>
            <ul>
                <li>Seg - Sex: 9:00 - 18:00</li>
                <li>Sáb: 9:00 - 14:00</li>
                <li>Dom: Fechado</li>
            </ul>
          </div>
        </div>

        <div class="base-mapa">
          <div class="mapa-container">
            <!-- Mudar para endereço certo -->
            <iframe
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3658.6070374974697!2d-46.54552332548977!3d-23.51065955972985!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x94ce5fb19f0e6cc5%3A0x7e36523a35868bca!2sAv.%20Canga%C3%ADba%2C%201153%20-%20Cangaiba%2C%20S%C3%A3o%20Paulo%20-%20SP!5e0!3m2!1spt-BR!2sbr!4v1731116355433!5m2!1spt-BR!2sbr"
              width="600"
              height="450"
              style="border: 0"
              allowfullscreen=""
              loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"
            ></iframe>
          </div>
        </div>
      </div>
    </section>

    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="./resources/js/homePage.js"></script>
  </body>
</html>
