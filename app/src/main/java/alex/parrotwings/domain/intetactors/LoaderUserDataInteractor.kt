package alex.parrotwings.domain.intetactors

import alex.parrotwings.data.remote.DtoMapper
import alex.parrotwings.data.remote.GetAllTransactionsResponse
import alex.parrotwings.data.remote.GetUserInfoResponse
import alex.parrotwings.domain.ApiServer
import alex.parrotwings.domain.sharedPreference.LocalRepository
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.LoaderUserDataInteractorListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class LoaderUserDataInteractor @Inject constructor(private val apiServer: ApiServer,
                                                   private val localRepository: LocalRepository) : TimerTask() {

    private var listener: LoaderUserDataInteractorListener? = null


    override fun run() {
        getAllTransaction()
        getUserInfo(localRepository.loadIdToken())
    }

    fun attachListener(presenter: LoaderUserDataInteractorListener) {
        listener = presenter
    }

    private fun getUserInfo(idToken: String) {
        apiServer.loadingUserInformation("Bearer $idToken")
                .enqueue(object : Callback<GetUserInfoResponse> {
                    override fun onFailure(call: Call<GetUserInfoResponse>?, t: Throwable?) {
                        listener?.onFailureLoading()
                    }

                    override fun onResponse(call: Call<GetUserInfoResponse>?, response: Response<GetUserInfoResponse>?) {
                        listener?.onSuccessLoading(DtoMapper.fromUserInfoDto(response?.body()?.user), response?.code())
                    }
                })
    }

    fun getAllTransaction() {
        apiServer.loadTransactions("Bearer ${localRepository.loadIdToken()}")
                .enqueue(object : Callback<GetAllTransactionsResponse> {
                    override fun onFailure(call: Call<GetAllTransactionsResponse>, t: Throwable?) {
                        listener?.onFailureTransactions()
                    }

                    override fun onResponse(call: Call<GetAllTransactionsResponse>?, response: Response<GetAllTransactionsResponse>?) {
                        if (response?.body() != null)
                            listener!!.onSuccessLoadTransactions(DtoMapper.fromTransaction(response!!.body()!!.transactions))
                    }
                })
    }
}