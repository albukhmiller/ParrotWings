package alex.parrotwings.presentations.ui.activities

import alex.parrotwings.App
import alex.parrotwings.R
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.presenters.LoginPresenter
import alex.parrotwings.presentations.mvp.views.LoginView
import alex.parrotwings.presentations.ui.base.BaseMvpActivity
import alex.parrotwings.utils.Validators
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.*
import org.jetbrains.anko.design.snackbar

class LoginActivity : BaseMvpActivity<LoginView, LoginPresenter>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setListeners()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity<PrimaryActivity>()
    }

    override fun onSuccessLogin(userToken: String) {
        startActivity(intentFor<AccountActivity>().clearTask())
        finish()
    }

    override fun onFailureLogin() {
        val view = snackbar(llLogin, getString(R.string.text_error_view)).view
        view.setBackgroundColor(getColor(android.R.color.holo_red_dark))
    }

    override fun showMessageError() {
        tvLoginError.visibility = View.VISIBLE
    }

    override fun hideMessageError() {
        tvLoginError.visibility = View.INVISIBLE
    }

    private fun setValidators() {
        edLogin.addValidator(Validators.EmailValidator("Вы ввели некорректный email"))
        edPass.addValidator(Validators.EmptyFieldValidator())
    }

    private fun setListeners() {
        btnLogin.setOnClickListener {
            setValidators()
            if (edLogin.validate() && edPass.validate())
                mvpPresenter.login(AuthInfo(edLogin.text.toString(), edPass.text.toString()))
        }
    }
}
