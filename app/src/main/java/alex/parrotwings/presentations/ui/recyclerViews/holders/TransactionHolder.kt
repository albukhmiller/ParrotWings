package alex.parrotwings.presentations.ui.recyclerViews.holders

import alex.parrotwings.R
import alex.parrotwings.models.Transaction
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.transaction_card.view.*
import kotlin.math.absoluteValue

class TransactionHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(transaction: Transaction, listener: (Transaction) -> Unit) {

        itemView.tvBalanceTransaction.text = transaction.balance.toString()
        itemView.tvDateTransaction.text = transaction.date
        itemView.tvReceiverTransaction.text = transaction.name

        if (transaction.amount < 0) {
            itemView.cwTransaction.setCardBackgroundColor(itemView.context.getColor(R.color.colorOutgoingTransaction))
        } else
            itemView.cwTransaction.setCardBackgroundColor(itemView.context.getColor(R.color.colorIncomingTransaction))
        itemView.tvAmountTransaction.text = transaction.amount.absoluteValue.toString()

        itemView.setOnClickListener {
            listener(transaction)
        }
    }
}