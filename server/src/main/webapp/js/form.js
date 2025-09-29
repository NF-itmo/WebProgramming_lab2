class Form {
    #formId

    constructor(formId){
        this.#formId = formId;
    }

    send(X,Y,R){
        const form = document.getElementById(this.#formId);

        // X
        form["X"].value = X.toFixed(2);

        // Y
        document.querySelectorAll(`input[name="Y"][value="${Y}"]`)[0].checked = true;

        form.submit();
    }
}