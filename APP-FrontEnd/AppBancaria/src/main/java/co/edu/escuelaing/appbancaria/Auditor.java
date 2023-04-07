package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Auditor {

    @GetMapping("/BancoAuditor")
    public String index() {
        return """
                <!DOCTYPE html>
                              <html>
                              <meta charset="UTF-8">
                              <title>W3.CSS Template</title>
                              <meta name="viewport" content="width=device-width, initial-scale=1">
                              <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
                              <body>
                              
                              <!-- Navbar (Sits on top) -->
                              <div class="w3-top w3-bar w3-white w3-wide w3-padding w3-card">
                              <a href="#home" class="w3-bar-item w3-button"><b>BI</b> Banco de Ingenieros</a>
                              <!-- Float links to the right. Hide them on small screens -->
                              <div class="w3-right w3-hide-small">
                              
                              <a href="#about" class="w3-bar-item w3-button">Gestión transaccional</a>
                              <a href="#contact" class="w3-bar-item w3-button">Soporte técnico</a>
                              </div>
                              </div>
               
                               <!-- Page Start -->
                              <div id="home" class="w3-content" style="max-width:1564px">
                              
                              <!-- Image -->
                              <div class="w3-display-container w3-content" style="max-width:1500px;">
                              <img class="w3-image" src="Bank.jpg" alt="Architecture" width="100%">
                              <div class="w3-display-middle w3-margin-top w3-center">
                              <h1 class="w3-xxlarge w3-text-white w3-wide">
                                <span class="w3-padding w3-black w3-opacity-min"><b>BI</b></span>
                                <span class="w3-hide-small w3-text-light-grey">Banco de ingenieros</span>
                              </h1>
                              </div>
                              </div>
               
                              <!-- About -->
                              <div id="about" class="w3-container w3-padding-32">
                              <h2 class="w3-border-bottom w3-border-light-grey w3-padding-16">Transferencias y movimientos</h2>
                              <p>Señor auditor. Recuerde que en esta sección podrá ver las transferencias entre 
                               usuarios adscritos a nuestra red bancaria, sin embargo no cuenta con permisos para modificarlas. 
                              Por favor proceda con cautela. Si tiene alguna dificultad, no dude en contactarnos.
                              </p>
                              </div>
                             
                                            <div class="w3-col l3 m6 w3-margin-bottom">
                                            <img src="Transferencia.jpg" alt="Mike" style="width:100%">
                                            <h3>Detalle de transferencias</h3>
                                            <p>Ingrese a este apartado para verificar el detalle de transferencias y otros movientos.</p>
                                            <p><form method="get" action="/Movimientos">
                                            <p><button class="w3-button w3-light-grey w3-block">Ver</button></form></p>
                                            </div>     
               
                                                          <div class="w3-col l3 m6 w3-margin-bottom">
                                                           <img src="CrearUsuario.jpg" alt="Mike" style="width:100%">
                                                           <h3>Información de Usuario</h3>
                                                           <p>Ingrese a este apartado para verificar la información de cada usuario.</p>
                                                           <p><form method="get" action="/AudiDetalles">
                                                           <p><button class="w3-button w3-light-grey w3-block">Ver</button></form></p>
                                                           </div>
               
               
               
               
               
                              <!-- Contact Section -->
                              <div id="contact" class="w3-container w3-padding-top-32">
                              
                              <h2 class="w3-border-bottom w3-border-light-grey w3-padding-16">Contacte con Soporte TI</h2>
                              <p>Contactenos y responderemos sus inquietudes en la mayor brevedad posible</p>
                              
                              <form action="/action_page.php" target="_blank">
                              <input class="w3-input w3-border" type="text" placeholder="Nombre" required name="Name">
                              <input class="w3-input w3-section w3-border" type="text" placeholder="Email" required name="Email">
                              <input class="w3-input w3-section w3-border" type="text" placeholder="Asunto" required name="Subject">
                              <input class="w3-input w3-section w3-border" type="text" placeholder="Comentarios" required name="Comment">
                              <button class="w3-button w3-black w3-section" type="submit">
                              <i class="fa fa-paper-plane"></i> Enviar mensaje
                              </button>
                              </form>
                              
                              <img src="SoporteTecnico.jpg" class="w3-image" style="width:100%">
                              
                              <footer class="w3-center w3-black w3-panel w3-padding-16">
                              <p>Powered by <a href="https://www.linkedin.com/in/debm23" title="W3.CSS" target="_blank" class="w3-hover-text-green">Bermúdez - Nontoa</a></p>
                              </footer>
                              </div>
                              
                              <!-- End page content -->
                              </div>
                              
                              </body>
                              </html>
               
               """;

    }
}
