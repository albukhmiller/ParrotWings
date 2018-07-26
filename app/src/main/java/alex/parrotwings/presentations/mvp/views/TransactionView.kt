package alex.parrotwings.presentations.mvp.views

import alex.parrotwings.models.Receiver

/**
 * Created by alex on 23.07.2018.
 */
interface TransactionView : BaseMvpView {

    fun onSuccessSearchReceiver(receiver: ArrayList<Receiver>)
    fun onFailureSearchReceiver()

    fun onSuccessTranscation()
    fun onFailureTransaction()
}