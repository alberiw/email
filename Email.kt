sealed class Email {
    abstract val value: String
    
    companion object {
        fun isValid(value: String): Boolean = value.contains("@")
        
        operator fun invoke(value: String): Email = when (isValid(value)) {
            true -> ValidEmail(value)
            false -> InvalidEmail(value)
        }
    }
}

data class ValidEmail(override val value: String): Email() {
    init {
        require(isValid(value)) { error("email is invalid") }
    }
}

data class InvalidEmail(override val value: String): Email() {
    init {
        require(!isValid(value)) { error("email is valid") }
    }
}
