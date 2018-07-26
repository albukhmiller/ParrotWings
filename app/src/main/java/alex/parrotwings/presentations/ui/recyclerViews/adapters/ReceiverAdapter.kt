package alex.parrotwings.presentations.ui.recyclerViews.adapters

import alex.parrotwings.R
import alex.parrotwings.models.Receiver
import alex.parrotwings.presentations.ui.recyclerViews.holders.ReceiverHolder
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class ReceiverAdapter(receivers: ArrayList<Receiver>,
                      private val listener: (Receiver) -> Unit) : RecyclerView.Adapter<ReceiverHolder>() {

    private var receivers :ArrayList<Receiver>? = null

    init {
        this.receivers = ArrayList()
        this.receivers!!.addAll(receivers)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = ReceiverHolder(LayoutInflater.from(parent?.context)
            .inflate(R.layout.receiver_layout, parent, false))

    override fun getItemCount() = receivers!!.size

    override fun onBindViewHolder(holder: ReceiverHolder, position: Int) = holder.bind(receivers!![position], listener)

    fun updateItem(receivers: ArrayList<Receiver>) {
        this.receivers!!.clear()
        this.receivers!!.addAll(receivers)
        notifyDataSetChanged()
    }

    fun cancelSearch(){
        receivers?.clear()
        notifyDataSetChanged()
    }
}