package alex.parrotwings.utils

import com.rengwuxian.materialedittext.validation.METValidator

class Validators {

    class EmailValidator(errorMessage: String) : METValidator(errorMessage) {

        override fun isValid(email: CharSequence, isEmpty: Boolean) = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    class PasswordOverlapValidator(private val repeatPassword: String, errorMessage: String) : METValidator(errorMessage) {

        override fun isValid(password: CharSequence, isEmpty: Boolean) = password.toString() == repeatPassword
    }

    class PasswordLengthValidator(errorMessage: String) : METValidator(errorMessage) {

        override fun isValid(password: CharSequence, isEmpty: Boolean) = password.toString().length > 5 || !password.toString().isEmpty()
    }

    class EmptyFieldValidator : METValidator("Поле не заполнено") {

        override fun isValid(amount: CharSequence, isEmpty: Boolean) = !amount.toString().isEmpty() && amount.toString() != " "
    }

    class AmountTransferredValidator(private val balance: Int, errorMessage: String) : METValidator(errorMessage) {

        override fun isValid(amount: CharSequence, isEmpty: Boolean): Boolean = amount.toString().toInt() < balance

    }
}