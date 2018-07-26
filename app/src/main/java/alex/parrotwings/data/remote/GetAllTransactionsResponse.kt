package alex.parrotwings.data.remote

import alex.parrotwings.data.remote.dtos.TransactionDto
import alex.parrotwings.models.Transaction
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 25.07.2018.
 */
data class GetAllTransactionsResponse(@SerializedName("trans_token") val transactions: ArrayList<TransactionDto>)