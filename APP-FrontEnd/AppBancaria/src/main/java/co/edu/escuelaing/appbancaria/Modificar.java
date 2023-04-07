package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Modificar {

    @GetMapping("/Modificar")
    public String index() {
        return """
              
              <!DOCTYPE html>
              <html>
                <head>
                  <title>Actualizar Saldo de Usuario</title>
                </head>
                <body>
                  <h1>Actualizar Saldo de Usuario</h1>
                  <form>
                    <label for="accountId">ID de cuenta:</label>
                    <input type="text" id="accountId" name="accountId"><br>
               
                  <label for="userId">ID de Usuario:</label>
                  <input type="text" id="userId" name="userId"><br>
              
                    <label for="amount">Monto:</label>
                    <input type="text" id="amount" name="amount"><br>
              
                    <button type="button" onclick="actualizarSaldo()">Actualizar Saldo</button>
              
              
              <script src="Mod.js"></script>
              
              
              
                  </form>
              </body>
              
             """;

    }

}
