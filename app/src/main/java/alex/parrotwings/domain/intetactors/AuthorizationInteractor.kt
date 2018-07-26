package alex.parrotwings.domain.intetactors

import alex.parrotwings.domain.ApiServer
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.BaseInteractorListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class AuthorizationInteractor @Inject constructor(private val apiServer: ApiServer) {

    private var listener: BaseInteractorListener? = null

    fun attachListener(presenter: BaseInteractorListener) {
        listener = presenter
    }

    fun registrationUser(user: AuthInfo) {
        apiServer.registrationUser(user)
                .enqueue(object : Callback<AuthInfo> {
                    override fun onFailure(call: Call<AuthInfo>?, t: Throwable?) {
                        listener?.onFailureLoading()
                    }

                    override fun onResponse(call: Call<AuthInfo>?, response: Response<AuthInfo>?) {
                        listener?.onSuccessLoading(response?.body()?.idToken, response?.code())
                    }
                })
    }

    fun loginUser(user: AuthInfo) {
        apiServer.loginUser(user)
                .enqueue(object : Callback<AuthInfo> {
                    override fun onFailure(call: Call<AuthInfo>?, t: Throwable?) {
                        listener?.onFailureLoading()
                    }

                    override fun onResponse(call: Call<AuthInfo>?, response: Response<AuthInfo>?) {
                        listener?.onSuccessLoading(response?.body()?.idToken, response?.code())
                    }
                })
    }
}