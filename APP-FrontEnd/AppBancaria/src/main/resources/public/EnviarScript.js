let jsonString = localStorage.getItem('autorizacion');
const autorizacion = JSON.parse(jsonString);
console.log(autorizacion);
const accountId = autorizacion.accountId;



function enviarDinero() {
  // Capturar los datos del formulario
  
  const destino = document.getElementById('destino').value;
  const monto = document.getElementById('monto').value;
  

  // Crear un objeto con los datos de los alimentos
  const dinero = {
    originAccount: accountId,
    destinationAccount: destino,
    amount: monto
    
  };

  // Enviar la petición al servidor en formato JSON
  fetch('http://localhost:9090/transfers', {
    method: 'POST',
    body: JSON.stringify(dinero),
    headers: {
      'Content-Type': 'application/json'
    }
  })
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));
}



