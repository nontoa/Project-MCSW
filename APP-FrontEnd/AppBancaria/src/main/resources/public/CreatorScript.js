



function crearUsuario() {
// Capturar los datos del formulario
        const id = document.getElementById('ID').value;
        const names = document.getElementById('nombre').value;
        const username = document.getElementById('username').value;
        const passW = document.getElementById('password').value;        
        const email = document.getElementById('email').value;   
        const phone = document.getElementById('phone').value;
        // Crear un objeto con los datos del usuario
        const usuario = {
            
                id : id,
                names: names,
                username :username,
                password : passW,
                email: email,
                phone : phone
                
        };
        // Enviar la petición al servidor en formato JSON
        fetch('http://localhost:9090/users', {
        method: 'POST',
                body: JSON.stringify(usuario),
                headers: {
                'Content-Type': 'application/json'
                }
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
        }
