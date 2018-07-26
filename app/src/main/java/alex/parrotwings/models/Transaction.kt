package alex.parrotwings.models

import android.os.Parcel
import android.os.Parcelable


data class Transaction(var name: String,
                       var amount: Int,
                       var date: String = "",
                       var balance: Int = 0,
                       var currentBalance: String = "") : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readString(),
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(amount)
        writeString(date)
        writeInt(balance)
        writeString(currentBalance)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Transaction> = object : Parcelable.Creator<Transaction> {
            override fun createFromParcel(source: Parcel): Transaction = Transaction(source)
            override fun newArray(size: Int): Array<Transaction?> = arrayOfNulls(size)
        }
    }
}