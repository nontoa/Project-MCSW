package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Enviar {

    @GetMapping("/Enviar")
    public String index() {
        return """
               <!DOCTYPE html>
                              <html>
                              <body>
               
               
               <form>
                 <label for="origen">Cuenta origen:</label>
                 <input type="text" id="origen" name="origen">
               
                 <label for="destino">Cuenta destino:</label>
                 <input type="text" id="destino" name="destino">
               
                 <label for="monto">Monto:</label>
                 <input type="text" id="monto" name="monto">
               
                 
               
                 <button onclick="enviarDinero()">Enviar Dinero</button>
                 
               </form>
               
                
                            <script src="./EnviarScript.js"> </script>
               
               
                              </body>
                              </html>
               """;

    }
}
