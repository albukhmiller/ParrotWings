package alex.parrotwings.presentations.mvp.presenters

import alex.parrotwings.models.Transaction
import alex.parrotwings.presentations.mvp.views.TransactionView

interface TransactionPresenter : BaseMvpPresenter<TransactionView> {

    fun searchReceiver(filter: String)

    fun createTransaction(name: String, amount: Int)
}