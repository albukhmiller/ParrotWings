package alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners

/**
 * Created by alex on 22.07.2018.
 */
interface BaseInteractorListener {

    fun <T> onSuccessLoading(data: T?, status: Int?)

    fun onFailureLoading()
}