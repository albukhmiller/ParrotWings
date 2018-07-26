package alex.parrotwings.data.remote

import alex.parrotwings.data.remote.dtos.TransactionDto
import alex.parrotwings.data.remote.dtos.UserInfoDto
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.models.Transaction
import alex.parrotwings.models.User

/**
 * Created by alex on 23.07.2018.
 */
class DtoMapper {

    companion object {
        fun fromUserInfoDto(userInfoDto: UserInfoDto?) = User(userInfoDto?.name, userInfoDto?.balance)

        fun fromTransaction(transactionDto: ArrayList<TransactionDto>): ArrayList<Transaction> {
            var transaction = ArrayList<Transaction>()

            for (trans in transactionDto)
                transaction.add(Transaction(trans.username, trans.amount, trans.date, trans.balance))

            return transaction
        }
    }
}