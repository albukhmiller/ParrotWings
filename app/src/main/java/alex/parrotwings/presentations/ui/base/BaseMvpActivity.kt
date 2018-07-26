package alex.parrotwings.presentations.ui.base

import alex.parrotwings.presentations.mvp.presenters.BaseMvpPresenter
import alex.parrotwings.presentations.mvp.views.BaseMvpView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

/**
 * Created by alex on 21.07.2018.
 */
abstract class BaseMvpActivity<in V : BaseMvpView, P : BaseMvpPresenter<V>> : AppCompatActivity() {

    @Inject
    protected lateinit var mvpPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            mvpPresenter.attachView(this as V)
        } catch (e: ClassCastException) {
            throw ClassCastException(this::class.toString()
                    + " does not implement the required BaseMvpView interface")
        }
    }

    override fun onDestroy() {
        mvpPresenter.detachView()
        super.onDestroy()
    }
}