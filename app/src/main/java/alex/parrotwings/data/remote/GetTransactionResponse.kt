package alex.parrotwings.data.remote

import alex.parrotwings.models.Transaction
import com.google.gson.annotations.SerializedName

/**
 * Created by alex on 25.07.2018.
 */
data class GetTransactionResponse(@SerializedName("trans_token") var transactionInfo: Transaction) {
}