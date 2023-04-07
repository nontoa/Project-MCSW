// definimos la URL de la API REST que vamos a consumir
const apiUrl = 'http://localhost:9090/users';

// obtenemos referencia al botón de consulta
const consultaBtn = document.getElementById('BTNusers');

// definimos una función para consultar la información de todos los usuarios
function consultarUsuarios() {
    // hacemos una petición GET a la API REST para obtener la información de todos los usuarios
    fetch(apiUrl)
            .then((response) => response.json())
            .then((data) => {
                // mostramos la información de todos los usuarios en la consola del navegador


                var Answer = data;
                const answerHTML = document.getElementById("userMovi");

                //answerHTML.textContent = "Los usuarios son:";
                console.log(Answer);
                console.log(answerHTML);

                Answer.map(Ans => {


                    const respuestaBox2 = document.createElement('div');
                    respuestaBox2.classList.add('respuesta-fetch2');

                    const respuestaTitle2 = document.createElement('h3');
                    respuestaTitle2.textContent = Ans.id;
                    respuestaBox2.appendChild(respuestaTitle2);

                    const respuestaList2 = document.createElement('ul');
                    respuestaBox2.appendChild(respuestaList2);

                    //Características según Postman

                    const idItem2 = document.createElement('li');
                    idItem2.innerHTML = '<strong>ID:</strong> <span></span>';
                    idItem2.querySelector('span').textContent = Ans.id;
                    respuestaList2.appendChild(idItem2);

                    const namesItem2 = document.createElement('li');
                    namesItem2.innerHTML = '<strong>Nombres:</strong> <span></span>';
                    namesItem2.querySelector('span').textContent = Ans.names;
                    respuestaList2.appendChild(namesItem2);



                    // Convertir el valor en milisegundos a un objeto Date
                    const createdDate = new Date(Ans.createdDate);

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
                    respuestaList2.appendChild(createdDateItem);


                    answerHTML.appendChild(respuestaBox2);


                });






            })
            .catch(error => {
                // en caso de error, mostramos un mensaje de error en la consola del navegador
                console.log(error);
                document.getElementById("userMovi").textContent = "Ocurrió un error al consultar los usuarios.";
            });
}

// agregamos un evento de click al botón de consulta que llame a la función para consultar todos los usuarios
consultaBtn.addEventListener('click', consultarUsuarios);



