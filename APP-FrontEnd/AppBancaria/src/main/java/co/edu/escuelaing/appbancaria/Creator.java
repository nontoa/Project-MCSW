package co.edu.escuelaing.appbancaria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Creator {

    @GetMapping("/Creator")
    public String index() {
        return """
               
               
                <!DOCTYPE html>
                <html>
                    <body>
                              
                        <h1>Creador de Usuarios</h1>
               
                <form>
               
                 <label for="ID">ID:</label>
                 <input type="text" id="ID" name="ID">
               
                 <label for="nombre">Nombre:</label>
                 <input type="text" id="nombre" name="nombre">
               
                 <label for="username">Nombre de usuario:</label>
                 <input type="username" id="username" name="username">
               
                 <label for="password">Password:</label>
                 <input type="password" id="password" name="password">
               
                 <label for="email">Correo electrónico:</label>
                 <input type="email" id="email" name="email">
                 
                 <label for="phone">Número celular:</label>
                 <input type="phone" id="phone" name="phone">
               
                 <label for="rol">Rol de usuario:</label>
                 <input type="rol" id="rol" name="rol">
               
                 <button onclick="crearUsuario()">Crear usuario</button>
               </form>
               
            
               
               <div id="movimientos"></div>
                                
                                
                   <script src="./CreatorScript.js"> </script>
               
               
                </body>
            </html>
               
               
               """;
    }
}
