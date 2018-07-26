package alex.parrotwings.presentations.mvp.views

/**
 * Created by alex on 22.07.2018.
 */
interface RegistrationView : BaseMvpView {

    fun onSuccessRegistration()
    fun onFailureRegistration()

    fun showMessageError()

    fun hideMessageError()
}