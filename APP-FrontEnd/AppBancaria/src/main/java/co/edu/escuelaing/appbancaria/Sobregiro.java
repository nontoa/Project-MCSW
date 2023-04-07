
package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sobregiro {
    
    @GetMapping("/Sobregiro")
    public String index() {
        return """
                <!DOCTYPE html>
                             <html>
                               <head>
                                 <title>Autorizar sobregiro</title>
                               </head>
                               <body>
                                 <h1>Datos de usuario</h1>
                                 <form>
                                   <label for="accountId">ID de Cuenta:</label>
                                   <input type="text" id="accountId" name="accountId"><br>
               
                                  <label for="userId">ID de Usuario:</label>
                                  <input type="text" id="userId" name="userId"><br>
                             
                                   <label for="amount">Monto del sobregiro:</label>
                                   <input type="text" id="amount" name="amount"><br>
                             
                                   <button type="button" onclick="actualizarSobregiro()">Actualizar Saldo</button>
                             
                             
                             <script src="Giro.js"></script>
                             
                             
                             
                                 </form>
                             </body>
                             """;
    
    }
    
}
