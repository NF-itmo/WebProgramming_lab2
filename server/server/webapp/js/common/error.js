class ErrorFactory {
    #errorBlockClassName

    constructor(errorBlockClassName){
        this.#errorBlockClassName = errorBlockClassName;
    }

    #clearErrors(){
        let existingErrors = document.querySelectorAll(this.#errorBlockClassName);
        existingErrors.forEach(error => error.remove());
    }

    showError(message, showTimeMs = 5000){
        this.#clearErrors();

        const errorDiv = document.createElement('div');
        errorDiv.className = this.#errorBlockClassName;
        errorDiv.textContent = message;

        document.body.appendChild(errorDiv);

        // Убираем по прошествии времени
        setTimeout(() => {
            if (errorDiv.parentNode) {
                errorDiv.parentNode.removeChild(errorDiv);
            }
        }, showTimeMs);
    }
}