package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AudiDetalles {

    @GetMapping("/AudiDetalles")
    public String index() {
        return """
              <!DOCTYPE html>
                             <html>
                             <body>
                             
                                 <h1>Consulta de usuarios y movimientos</h1>
                                 <form>
                             
                             
                                 <button id="BTNusers" type="button" onclick="consultarUsuarios()">Consultar Usuarios</button>
                                 
               
                                </form>
                               
                             
                                 <div id="userMovi"></div>
                               
                               
                             <script src="./userMovi.js"> </script>  
                             
                            
                             
                             </body>
                             </html>""";

    }
}
