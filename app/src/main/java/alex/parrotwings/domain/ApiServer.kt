package alex.parrotwings.domain

import alex.parrotwings.data.remote.GetTransactionResponse
import alex.parrotwings.data.remote.GetUserInfoResponse
import alex.parrotwings.data.remote.GetAllTransactionsResponse
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.models.Receiver
import alex.parrotwings.models.Transaction
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiServer {

    @POST("/users")
    fun registrationUser(@Body user: AuthInfo): Call<AuthInfo>

    @POST("/sessions/create")
    fun loginUser(@Body user: AuthInfo): Call<AuthInfo>

    @GET("/api/protected/user-info")
    fun loadingUserInformation(@Header("Authorization") authHeader: String): Call<GetUserInfoResponse>

    @POST("/api/protected/users/list")
    fun searchReceiver(@Header("Authorization") authHeader: String, @Body filter: Receiver): Call<ArrayList<Receiver>>

    @POST("/api/protected/transactions")
    fun createTransaction(@Header("Authorization") authHeader: String, @Body transaction: Transaction): Call<GetTransactionResponse>

    @GET("/api/protected/transactions")
    fun loadTransactions(@Header("Authorization") authHeader: String): Call<GetAllTransactionsResponse>
}