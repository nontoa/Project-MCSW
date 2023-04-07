package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Saldo {

    @GetMapping("/Saldo")
    public String index() {
        return """
               <!DOCTYPE html>
               <html>
               <body>
               
                   <h1>Consulta de saldo</h1>
                   <form>
                     <label for="accountId">ID de la cuenta:</label>
                     <input type="text" id="accountId" name="accountId">
                     <button type="button" onclick="consultarSaldo()">Consultar saldo</button>
                   </form>
               
                   <div id="saldo"></div>
                 
                 
               <script src="./BalanceScript.js"> </script>  
               
              
               
               </body>
               </html>""";
    }
}
