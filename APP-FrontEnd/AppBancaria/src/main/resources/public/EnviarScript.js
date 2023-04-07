



function enviarDinero() {
  // Capturar los datos del formulario
  const origen = document.getElementById('origen').value;
  const destino = document.getElementById('destino').value;
  const monto = document.getElementById('monto').value;
  

  // Crear un objeto con los datos de los alimentos
  const dinero = {
    originAccount: origen,
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



