
package co.edu.escuelaing.appbancaria;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferAdmin {
    
        @GetMapping("/TransferAdmin")
        public String index() {
        return """
                               <!DOCTYPE html>
                                            <html>
                                              <head>
                                                <title>Consultar transferencias de usuarios</title>
                                              </head>
                                              <body>
                                                <h1>Datos de usuario</h1>
                                                <form>
                                              
                                                 <label for="userId">ID de Usuario:</label>
                                                 <input type="text" id="userId" name="userId"><br>
                                            
                                                  <button type="button" onclick="consultarMovimientos_ADMIN()">Ver movimientos</button>
                                            
                                            <div id="movimientos_ADMIN"></div>
                                            <script src="movS_Admin.js"></script>
                                            
                                            
                                            
                                                </form>
                                            </body> """;
}

}
               
               
               
