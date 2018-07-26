package alex.parrotwings.presentations.ui.activities

import alex.parrotwings.App
import alex.parrotwings.R
import alex.parrotwings.models.Receiver
import alex.parrotwings.models.Transaction
import alex.parrotwings.models.User
import alex.parrotwings.presentations.mvp.presenters.TransactionPresenter
import alex.parrotwings.presentations.mvp.views.TransactionView
import alex.parrotwings.presentations.ui.base.BaseMvpActivity
import alex.parrotwings.presentations.ui.recyclerViews.adapters.ReceiverAdapter
import alex.parrotwings.utils.Validators
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_transaction.*
import kotlinx.android.synthetic.main.search_layout.*
import kotlinx.android.synthetic.main.transaction_toolbar.*
import org.jetbrains.anko.design.snackbar
import kotlin.math.absoluteValue


class TransactionActivity : BaseMvpActivity<TransactionView, TransactionPresenter>(), TransactionView {

    private var receiverAdapter: ReceiverAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        initBalanceView()
        initRecyclerEvent()
        setListeners()
    }

    override fun onSuccessSearchReceiver(receiver: ArrayList<Receiver>) {
        if (receiverAdapter == null) {
            receiverAdapter = ReceiverAdapter(receiver) {
                edSearchUser.setText(it.name, TextView.BufferType.EDITABLE)
                btnSearchBack.visibility = View.GONE
                searcgLayout.visibility = View.GONE
            }
            rvReceivers.adapter = receiverAdapter
        }

        if (receiver.size == 0)
            tvNoSuchUser.visibility = View.VISIBLE
        else
            tvNoSuchUser.visibility = View.GONE

        receiverAdapter?.updateItem(receiver)
    }

    override fun onFailureSearchReceiver() {
        showSnackbar()
    }

    override fun onSuccessTranscation() {
        finish()
    }

    override fun onFailureTransaction() {
        showSnackbar()
    }

    private fun initBalanceView() {
        val transaction: Transaction? = intent.getParcelableExtra("trans")
        val user: User? = intent.getParcelableExtra("user")

        if (transaction != null) {
            edAmount.setText(transaction.amount.absoluteValue.toString(), TextView.BufferType.EDITABLE)
            edSearchUser.setText(transaction.name, TextView.BufferType.EDITABLE)
            tvSumTransaction.text = transaction.currentBalance
        } else
            tvSumTransaction.text = user!!.balance.toString()
    }

    private fun setValidators() {
        edAmount.addValidator(Validators.EmptyFieldValidator())
                .addValidator(Validators.AmountTransferredValidator(tvSumTransaction.text.toString().toInt(), "Пересылаемая сумма должна быть меньше баланса"))


        edSearchUser.addValidator(Validators.EmptyFieldValidator())
    }

    private fun setListeners() {

        edSearchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() != "") {
                    btnSearchBack.visibility = View.VISIBLE
                    searcgLayout.visibility = View.VISIBLE
                }

                if (!p0?.trim()!!.isEmpty())
                    mvpPresenter.searchReceiver(p0.toString())
            }
        })

        btnSearchBack.setOnClickListener {
            btnSearchBack.visibility = View.GONE
            searcgLayout.visibility = View.GONE

            receiverAdapter?.cancelSearch()
            hideKeyboard()
            edSearchUser.setText("", TextView.BufferType.EDITABLE)
        }

        btnSendMoney.setOnClickListener {
            setValidators()
            hideKeyboard()
            if (edAmount.validate() && edSearchUser.validate())
                mvpPresenter.createTransaction(edSearchUser.text.toString(), edAmount.text.toString().toInt())
            else
                edAmount.clearValidators()
        }
    }

    private fun initRecyclerEvent() {
        rvReceivers.layoutManager = LinearLayoutManager(this)
        rvReceivers.setHasFixedSize(true)
    }

   private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
        edSearchUser.clearFocus()
        edSearchUser.isCursorVisible = false

    }
    private fun showSnackbar() {
        val view = snackbar(llTrans, getString(R.string.text_error_view)).view
        view.setBackgroundColor(getColor(android.R.color.holo_red_dark))
    }

}