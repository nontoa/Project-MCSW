function consultarMovimientos_ADMIN() {
    const userId = document.getElementById("userId").value;
const url = `http://localhost:9090/transfers/${userId}`;   //cambiar esto con ID

    fetch(url)
            .then((response) => response.json())
            .then((data) => {

                var movimientos_ADMIN = data;
                const movimientos_ADMINHTML = document.getElementById("movimientos_ADMIN");
                console.log(movimientos_ADMINHTML);
                movimientos_ADMINHTML.textContent = `Los movimientos del usuario son ${userId}`;

                movimientos_ADMIN.map(movimiento => {

                    const respuestaBox = document.createElement('div');
                    respuestaBox.classList.add('respuesta-fetch');

                    const respuestaTitle = document.createElement('h3');
                    respuestaTitle.textContent = movimiento.id;
                    respuestaBox.appendChild(respuestaTitle);

                    const respuestaList = document.createElement('ul');
                    respuestaBox.appendChild(respuestaList);

                    const idItem = document.createElement('li');
                    idItem.innerHTML = '<strong>ID:</strong> <span></span>';
                    idItem.querySelector('span').textContent = movimiento.id;
                    respuestaList.appendChild(idItem);

                    const originItem = document.createElement('li');
                    originItem.innerHTML = '<strong>Cuenta de origen:</strong> <span></span>';
                    originItem.querySelector('span').textContent = movimiento.originAccount;
                    respuestaList.appendChild(originItem);

                    const destinationItem = document.createElement('li');
                    destinationItem.innerHTML = '<strong>Cuenta de destino:</strong> <span></span>';
                    destinationItem.querySelector('span').textContent = movimiento.destinationAccount;
                    respuestaList.appendChild(destinationItem);

                    const amountItem = document.createElement('li');
                    amountItem.innerHTML = '<strong>Monto:</strong> <span></span>';
                    amountItem.querySelector('span').textContent = movimiento.amount;
                    respuestaList.appendChild(amountItem);



                    // Convertir el valor en milisegundos a un objeto Date
                    const createdDate = new Date(movimiento.createdDate);

// Formatear la fecha en una cadena de texto en el formato deseado
                    const dia = createdDate.getDate().toString().padStart(2, '0');
                    const mes = (createdDate.getMonth() + 1).toString().padStart(2, '0');
                    const año = createdDate.getFullYear().toString();
                    const hora = createdDate.getHours().toString().padStart(2, '0');
                    const minutos = createdDate.getMinutes().toString().padStart(2, '0');
                    const segundos = createdDate.getSeconds().toString().padStart(2, '0');
                    const fechaFormateada = `${dia}/${mes}/${año} ${hora}:${minutos}:${segundos}`;

                    const createdDateItem = document.createElement('li');
                    createdDateItem.innerHTML = '<strong>Fecha de creación:</strong> <span></span>';
                    createdDateItem.querySelector('span').textContent = createdDate;
                    respuestaList.appendChild(createdDateItem);




                    movimientos_ADMINHTML.appendChild(respuestaBox);

                });



            })
            .catch((error) => {
                console.error(error);
                document.getElementById("movimientos_ADMIN").textContent = "Ocurrió un error al consultar el saldo de la cuenta.";
            });
}

                