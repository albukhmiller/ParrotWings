package alex.parrotwings.data.remote.dtos

/**
 * Created by alex on 25.07.2018.
 */
data class TransactionDto(var username: String,
                          var amount: Int,
                          var date: String,
                          var balance: Int)