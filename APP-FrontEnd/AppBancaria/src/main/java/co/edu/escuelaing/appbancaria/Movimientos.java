
package co.edu.escuelaing.appbancaria;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Movimientos {
    @GetMapping("/Movimientos")
    public String index() {
        return """
<!DOCTYPE html>
               <html>
               <body>
               
                   <h1>Consulta de movimientos</h1>
                   <form>
               
               
              
               
               
                     <label for="accountId">ID de la cuenta:</label>
                     <input type="text" id="accountId" name="accountId">
                     <button type="button" onclick="consultarMovimientos()">Consultar Movimientos</button>
                   </form>
               
                   <div id="movimientos"></div>
                 
                 
               <script src="./MovScript.js"> </script>  
               
              
               
               </body>
               </html>
               """;
    }
}


