
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
          
               
                   <div id="movimientos"></div>
                 
                 
                 <script src="./movS.js"> </script>
               
              
               
               </body>
               </html>
               """;
    }
}


