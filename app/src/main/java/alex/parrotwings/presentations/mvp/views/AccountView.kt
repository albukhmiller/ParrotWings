package alex.parrotwings.presentations.mvp.views

import alex.parrotwings.models.Transaction
import alex.parrotwings.models.User

/**
 * Created by alex on 23.07.2018.
 */
interface AccountView : BaseMvpView {

    fun onSuccessLoadUserData(user: User)

    fun onFailureLoadUserData()

    fun openAuthorization()

    fun onSuccessLoadTransaction(transactions: ArrayList<Transaction>)

    fun onFailureLoadTransactions()
}