package alex.parrotwings.presentations.ui.activities

import alex.parrotwings.App
import alex.parrotwings.R
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.presenters.RegistrationPresenter
import alex.parrotwings.presentations.mvp.views.RegistrationView
import alex.parrotwings.presentations.ui.base.BaseMvpActivity
import alex.parrotwings.utils.Validators
import android.os.Bundle
import android.view.View
import com.rengwuxian.materialedittext.validation.RegexpValidator
import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class RegistrationActivity : BaseMvpActivity<RegistrationView, RegistrationPresenter>(), RegistrationView {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        setListeners()
    }

    override fun onSuccessRegistration() {
        startActivity(intentFor<AccountActivity>().clearTask())
        finish()
    }

    override fun onFailureRegistration() {
        var view = snackbar(llRegistration, getString(R.string.text_error_view)).view
        view.setBackgroundColor(getColor(android.R.color.holo_red_dark))
    }

    override fun showMessageError() {
        tvRegistrationError.visibility = View.VISIBLE
    }

    override fun hideMessageError() {
        tvRegistrationError.visibility = View.INVISIBLE
    }

    private fun setValidationFields() {
        edUsername.addValidator(RegexpValidator("Имя должно содержать только буквы", "\\D+"))
        edEmail.addValidator(Validators.EmailValidator("Вы ввели некорректный email"))
        edPassword.addValidator(Validators.PasswordOverlapValidator(edRepeatedPassword.text.toString(), "Введенные пароли не совпадают"))
                .addValidator(Validators.PasswordLengthValidator("Пароль должен содержать минимум 6 символов"))
    }

    private fun setListeners() {
        btnReg.setOnClickListener {
            setValidationFields()

            if (edUsername.validate() && edUsername.validate() && edPassword.validate() && edEmail.validate())
                mvpPresenter.redistrationNewUser(AuthInfo(edEmail.text.toString(), edPassword.text.toString(), edUsername.text.toString()))

            edPassword.clearValidators()
        }
    }
}
