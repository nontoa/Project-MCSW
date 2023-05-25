let jsonString = localStorage.getItem('autorizacion');
const autorizacion = JSON.parse(jsonString);
console.log(autorizacion);
const accountId = autorizacion.userId;


const url = `http://localhost:9090/users/totalBalance/${accountId}`;

fetch(url)
        .then((response) => response.json())
        .then((data) => {
            var saldo = data;
            document.getElementById("saldo").textContent = `El saldo de la cuenta ${accountId} es de ${saldo} dólares.`;
        })
        .catch((error) => {
            console.error(error);
            document.getElementById("saldo").textContent = "Ocurrió un error al consultar el saldo de la cuenta.";
        });