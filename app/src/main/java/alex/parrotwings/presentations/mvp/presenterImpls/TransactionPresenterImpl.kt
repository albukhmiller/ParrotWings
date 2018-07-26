package alex.parrotwings.presentations.mvp.presenterImpls

import alex.parrotwings.domain.intetactors.TransactionInteractor
import alex.parrotwings.domain.sharedPreference.LocalRepository
import alex.parrotwings.models.Receiver
import alex.parrotwings.models.Transaction
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.BaseInteractorListener
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.TransactionInteracorListener
import alex.parrotwings.presentations.mvp.presenters.TransactionPresenter
import alex.parrotwings.presentations.mvp.views.TransactionView
import javax.inject.Inject

/**
 * Created by alex on 23.07.2018.
 */
class TransactionPresenterImpl @Inject constructor(private val transactionInteractor: TransactionInteractor,
                                                   private var localRepository: LocalRepository) : BasePresenterImpl<TransactionView>(), TransactionPresenter, TransactionInteracorListener {

    init {
        transactionInteractor.setListener(this)
    }

    override fun searchReceiver(filter: String) {
        transactionInteractor.getSearchReceiver(localRepository.loadIdToken(), filter)
    }

    override fun createTransaction(name: String, amount: Int) {
        transactionInteractor.sendMoney(Transaction(name, amount))
    }

    override fun <T> onSuccessLoading(data: T?, status: Int?) {
        if (data != null)
            mView?.onSuccessSearchReceiver(data as ArrayList<Receiver>)
        else
            mView?.onSuccessSearchReceiver(arrayListOf())
    }

    override fun onFailureLoading() {
        mView?.onFailureSearchReceiver()
    }

    override fun onSuccessTransaction() {
        mView?.onSuccessTranscation()
    }

    override fun onFailureTransaction() {
        mView?.onFailureTransaction()
    }
}