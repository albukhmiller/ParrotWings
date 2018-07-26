package alex.parrotwings.presentations.mvp.presenters

import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.views.RegistrationView

/**
 * Created by alex on 22.07.2018.
 */
interface RegistrationPresenter: BaseMvpPresenter<RegistrationView> {

    fun redistrationNewUser(user: AuthInfo)
}