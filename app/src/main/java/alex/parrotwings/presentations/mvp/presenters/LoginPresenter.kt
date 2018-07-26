package alex.parrotwings.presentations.mvp.presenters

import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.views.LoginView

/**
 * Created by alex on 21.07.2018.
 */
interface LoginPresenter : BaseMvpPresenter<LoginView> {

    fun login(user: AuthInfo)


}