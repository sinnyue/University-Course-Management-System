const faqs = document.querySelectorAll(".faq");

faqs.forEach(faq => {
    faq.addEventListener("click",() => {
        faq.classList.toggle("active");
    });
});

window.onload = init;

function init() {
    document.forms[0].onsubmit = function() {
       if (this.checkValidity()) alert("Data passes initial validation tests, Succesfully Send!");
       return false;
    }   
   document.getElementById("faq").onclick=turnOnfaq;
 }
 function turnOnfqf() {
    document.getElementById("questionname").disabled=true;
    document.getElementById("questionemail").disabled=true;
    document.getElementById("questionemail").disabled=true;
 }