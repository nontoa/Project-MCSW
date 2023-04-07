



const form = document.querySelector('#login-form');
const message = document.querySelector('#login-message');
form.addEventListener('submit', (event) => {
    event.preventDefault();
    const fname = document.querySelector('#fname').value;
    const passID = document.querySelector('#passID').value;
    var data = {
        userName: fname,
        password: passID
    };
    console.log(data);
    fetch('http://localhost:9090/users/authentication', {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    })
            .then(response => response.json())
            .then(data => {
                console.log(data);

                if (data.authentication === 'Valid') {

                    message.textContent = 'Inicio de sesión exitoso';
                    if (fname === 'user1' && passID === 'user1' ) {

                        window.location.replace("/BancoAdmin");

                    } else if (fname === 'user4' && passID === 'user4' ) {

                        window.location.replace("/BancoAuditor");

                    } else {

                        window.location.replace("/BancoUsuario");
                    }



                } else {
                    message.textContent = 'Inicio de sesión fallido';
                }
            })


            .catch(error => {
                message.textContent = 'Ha ocurrido un error al iniciar sesión';
                console.error(error);
            });

});
