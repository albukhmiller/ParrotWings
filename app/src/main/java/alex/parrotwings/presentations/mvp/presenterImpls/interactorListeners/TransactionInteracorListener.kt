package alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners

/**
 * Created by alex on 25.07.2018.
 */
interface TransactionInteracorListener : BaseInteractorListener {

    fun onSuccessTransaction()
    fun onFailureTransaction()
}