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

                   <div id="saldo"></div>
                 
                 
               <script src="./consultaSaldo.js"> </script>  
               
              
               
               </body>
               </html>""";
    }
}
