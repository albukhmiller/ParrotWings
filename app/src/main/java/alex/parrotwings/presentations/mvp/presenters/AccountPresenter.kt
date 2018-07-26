package alex.parrotwings.presentations.mvp.presenters

import alex.parrotwings.models.User
import alex.parrotwings.presentations.mvp.views.AccountView


interface AccountPresenter: BaseMvpPresenter<AccountView> {

    fun getUser(): User

    fun logout()

    fun loadUserInfo()

    fun loadTransactions()

    fun stopTimer()
}