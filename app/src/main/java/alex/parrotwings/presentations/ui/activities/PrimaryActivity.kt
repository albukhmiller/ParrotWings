package alex.parrotwings.presentations.ui.activities

import alex.parrotwings.R
import alex.parrotwings.data.local.sharedPreference.LocalRepository
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_primary.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity

class PrimaryActivity : AppCompatActivity() {

    private val CODE_LOGIN_REQUEST = 0
    private val CODE_REGISTRATION_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary)

        authCheck()
        setListener()
    }

    private fun setListener() {
        btnOpenLogin.setOnClickListener {
            startActivityForResult(Intent(this, LoginActivity::class.java), CODE_LOGIN_REQUEST)
//            finish()
        }

        btnOpenRegistration.setOnClickListener {
            startActivityForResult(Intent(this, RegistrationActivity::class.java), CODE_REGISTRATION_REQUEST)
//            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            startActivity<AccountActivity>()
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun authCheck() {
        val idToken = LocalRepository(this).loadIdToken()
        if (idToken != "") {
            startActivity(intentFor<AccountActivity>().singleTop())
            finish()
        }
    }
}
