package alex.parrotwings.presentations.ui.activities

import alex.parrotwings.App
import alex.parrotwings.R
import alex.parrotwings.models.Transaction
import alex.parrotwings.models.User
import alex.parrotwings.presentations.mvp.presenters.AccountPresenter
import alex.parrotwings.presentations.mvp.views.AccountView
import alex.parrotwings.presentations.ui.base.BaseMvpActivity
import alex.parrotwings.presentations.ui.recyclerViews.adapters.TransactionAdapter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.account_toolbar.*
import kotlinx.android.synthetic.main.activity_account.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class AccountActivity : BaseMvpActivity<AccountView, AccountPresenter>(), AccountView {

    private var adapterTransactions: TransactionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setSupportActionBar(account_toolbar)

        setListener()

        rvTransactions.layoutManager = LinearLayoutManager(this)
        rvTransactions.setHasFixedSize(true)

        mvpPresenter.loadUserInfo()
        mvpPresenter.loadTransactions()

    }

    override fun onResume() {
        mvpPresenter.loadTransactions()
        super.onResume()
    }

    override fun onDestroy() {
        mvpPresenter.stopTimer()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.btnLogout -> {
                mvpPresenter.stopTimer()
                mvpPresenter.logout()
                startActivity<PrimaryActivity>()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSuccessLoadUserData(user: User) {
        tvBalance.text = user.balance.toString()
        tvNameUser.text = user.name
    }

    override fun onFailureLoadUserData() {}

    override fun openAuthorization() {
        startActivity<PrimaryActivity>()
        finish()
    }

    override fun onSuccessLoadTransaction(transactions: ArrayList<Transaction>) {
        layoutError.visibility = View.GONE
        if (adapterTransactions == null) {
            adapterTransactions = TransactionAdapter(transactions) {
                if (it.amount < 0) {
                    it.currentBalance = tvBalance.text.toString()
                    startActivity(intentFor<TransactionActivity>("trans" to it))
                }
            }
            rvTransactions.adapter = adapterTransactions
        }
        adapterTransactions!!.updateTransactions(transactions)
    }

    override fun onFailureLoadTransactions() {
       layoutError.visibility = View.VISIBLE
    }

    private fun setListener() {
        btnCreateTransaction.setOnClickListener {
            startActivity(intentFor<TransactionActivity>("user" to mvpPresenter.getUser()))
        }

    }

}
