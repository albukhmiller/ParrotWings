package alex.parrotwings.presentations.ui.activities

import alex.parrotwings.R
import alex.parrotwings.domain.sharedPreference.LocalRepository
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_primary.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

class PrimaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary)

        authCheck()
        setListener()
    }

    private fun setListener() {
        btnOpenLogin.setOnClickListener {
            startActivity<LoginActivity>()
            finish()
        }

        btnOpenRegistration.setOnClickListener {
            startActivity<RegistrationActivity>()
            finish()
        }
    }

    fun authCheck() {
        val idToken = LocalRepository(this).loadIdToken()
        if (idToken != "") {
            startActivity(intentFor<AccountActivity>().singleTop())
            finish()
        }
    }
}
