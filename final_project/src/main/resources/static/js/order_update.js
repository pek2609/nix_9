document.getElementById("client").onclick = function () {
    let option = this.options[this.selectedIndex];

    let inputName = document.getElementById("name");
    let inputSurname = document.getElementById("surname");
    let inputPhone = document.getElementById("phone");

    if (option.value) {
        let name = option.getAttribute("first_name");
        let surname = option.getAttribute("last_name");
        let phone = option.getAttribute("phone");
        setInputs(name, surname, phone);
        addReadonly();
    } else {
        removeReadonly();
        setInputs("", "", "");
    }

    function setInputs(name, surname, phone){
        inputName.value = name;
        inputSurname.value = surname;
        inputPhone.value = phone;
    }

    function addReadonly(){
        inputSurname.setAttribute("readonly", "readonly");
        inputName.setAttribute("readonly", "readonly");
        inputPhone.setAttribute("readonly", "readonly");
    }

    function removeReadonly(){
        inputName.removeAttribute("readonly");
        inputSurname.removeAttribute("readonly");
        inputPhone.removeAttribute("readonly");
    }
};


