window.onload = init;

function init() {
    // Function 1
    document.forms[0].onsubmit = function() {
        if (this.checkValidity()) alert("Data passes initial validation tests");
        return false;
    };
    //Function 1 is use first
    document.getElementById("feedback").onclick = turnOnfeedback;

    // Function 2
    document.forms[0].onsubmit = function() {
        if (this.checkValidity()) {
            document.getElementById("namebox").disabled = true;
            document.getElementById("phonebox").disabled = true;
            document.getElementById("boxemail").disabled = true;
            // Display popup message
            var popup = document.createElement("div");
            popup.classList.add("popup");
            var text = document.createTextNode("Thank you! Your details have been successfully submitted.");
            popup.appendChild(text);
            document.body.appendChild(popup);
            this.reset();
            return false;
        }
        return false;
    };

    // Function 3
    document.forms[0].onsubmit = function() {
        if (this.checkValidity()) {
            if (confirm("Are you sure you would like to submit the feedback?")){
                alert("successfully submitted!\n\n" + "Name: " + document.getElementById("namebox").value + "\n" + "Tel No: " + document.getElementById("phonebox").value + "\n");
                return false;  
            }else{
                return false;
            }
        }else{
            return false;
        }
    };
}

