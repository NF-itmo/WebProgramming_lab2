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

        // Слздаём элемент
        const errorDiv = document.createElement('div');
        errorDiv.className = this.#errorBlockClassName;
        errorDiv.textContent = message;

        // Вставка
        document.body.appendChild(errorDiv);

        // Убираем по прошествии времени
        setTimeout(() => {
            if (errorDiv.parentNode) {
                errorDiv.parentNode.removeChild(errorDiv);
            }
        }, showTimeMs);
    }
}