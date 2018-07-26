package alex.parrotwings.presentations.mvp.views

/**
 * Created by alex on 21.07.2018.
 */
interface LoginView : BaseMvpView {

    fun onSuccessLogin(userToken: String)

    fun onFailureLogin()

    fun showMessageError()

    fun hideMessageError()

}