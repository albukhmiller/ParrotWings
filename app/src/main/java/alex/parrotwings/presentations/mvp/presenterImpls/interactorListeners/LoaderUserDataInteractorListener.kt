package alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners

import alex.parrotwings.models.Transaction

/**
 * Created by alex on 25.07.2018.
 */
interface LoaderUserDataInteractorListener : BaseInteractorListener {

    fun onSuccessLoadTransactions(transactions: ArrayList<Transaction>)

    fun onFailureTransactions()
}