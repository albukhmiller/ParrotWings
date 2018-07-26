package alex.parrotwings.domain.intetactors

import alex.parrotwings.data.remote.GetTransactionResponse
import alex.parrotwings.domain.ApiServer
import alex.parrotwings.domain.sharedPreference.LocalRepository
import alex.parrotwings.models.Receiver
import alex.parrotwings.models.Transaction
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.TransactionInteracorListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class TransactionInteractor @Inject constructor(private val apiServer: ApiServer,
                                                private val localRepository: LocalRepository) {

    private var listener: TransactionInteracorListener? = null

    fun setListener(presenter: TransactionInteracorListener) {
        listener = presenter
    }

    fun getSearchReceiver(idToken: String, filter: String) {

        apiServer.searchReceiver("Bearer $idToken", Receiver(filter))
                .enqueue(object : Callback<ArrayList<Receiver>> {
                    override fun onFailure(call: Call<ArrayList<Receiver>>?, t: Throwable?) {
                        listener?.onFailureLoading()
                    }

                    override fun onResponse(call: Call<ArrayList<Receiver>>?, response: Response<ArrayList<Receiver>>?) {
                        listener?.onSuccessLoading(response?.body(), response?.code())
                    }
                })
    }

    fun sendMoney(transaction: Transaction) {
        apiServer.createTransaction("Bearer ${localRepository.loadIdToken()}", transaction)
                .enqueue(object : Callback<GetTransactionResponse> {
                    override fun onFailure(call: Call<GetTransactionResponse>?, t: Throwable?) {
                        listener?.onFailureTransaction()
                    }

                    override fun onResponse(call: Call<GetTransactionResponse>?, response: Response<GetTransactionResponse>?) {
                        listener?.onSuccessTransaction()
                    }
                })
    }
}