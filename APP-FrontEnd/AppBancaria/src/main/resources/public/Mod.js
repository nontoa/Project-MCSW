


 function actualizarSaldo() {
        var accountId = (document.getElementById("accountId").value);
        var userId = (document.getElementById("userId").value);
        var amount = document.getElementById("amount").value;
       
        

        fetch(`http://localhost:9090/users/${userId}/account/balance`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({ amount: amount, accountId: accountId })
        })
        .then(response => {
          if (response.ok) {
            alert("Saldo actualizado correctamente.");
          } else {
            alert("No tiene fondos suficientes");
          }
        })
        .catch(error => {
          console.error('Error:', error);
          alert("Error al actualizar el saldo.");
        });
      }