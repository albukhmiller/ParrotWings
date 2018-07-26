package alex.parrotwings.presentations.ui.recyclerViews.holders

import alex.parrotwings.models.Receiver
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.receiver_layout.view.*

class ReceiverHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Receiver, listener: (Receiver) -> Unit) {
        itemView.tvReceiver.text = item.name
        itemView.tvReceiver.setOnClickListener { listener(item) }
    }
}