package alex.parrotwings.presentations.ui.recyclerViews.adapters

import alex.parrotwings.R
import alex.parrotwings.models.Transaction
import alex.parrotwings.presentations.ui.recyclerViews.holders.TransactionHolder
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by alex on 25.07.2018.
 */
class TransactionAdapter(items: ArrayList<Transaction>, private val listener: (Transaction) -> Unit) : RecyclerView.Adapter<TransactionHolder>() {

    private var transactions: ArrayList<Transaction> = items.reversed().toCollection(ArrayList())

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = TransactionHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.transaction_card, parent, false))

    override fun getItemCount() = transactions.size


    override fun onBindViewHolder(holder: TransactionHolder, position: Int) = holder.bind(transactions[position], listener)


    fun updateTransactions(newTransactions: ArrayList<Transaction>) {
        transactions.clear()
        transactions.addAll(newTransactions.reversed().toCollection(ArrayList()))
        notifyDataSetChanged()
    }
}