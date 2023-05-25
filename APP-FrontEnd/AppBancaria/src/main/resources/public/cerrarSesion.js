const button= document.getElementById("cerrar");
button.addEventListener("click", myFunction);

function myFunction() {
  
  localStorage.clear();
  window.location.replace("/LogBank.html");

}


