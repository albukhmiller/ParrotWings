package alex.parrotwings.data.local.sharedPreference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(private val context: Context) {

    private var repository: SharedPreferences? = null

    init {
         repository = context.getSharedPreferences("IdToken", MODE_PRIVATE)
    }

    fun saveIdToken(name: String, value: String) {
        val ed = repository!!.edit()
        ed.putString(name, value)
        ed.commit()
    }

    fun loadIdToken() = repository!!.getString("idToken", "")


    fun deleteIdToken() = repository!!.edit().clear().commit()
}