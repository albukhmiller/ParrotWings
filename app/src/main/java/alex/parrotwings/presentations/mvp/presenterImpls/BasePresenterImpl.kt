package alex.parrotwings.presentations.mvp.presenterImpls

import alex.parrotwings.presentations.mvp.presenters.BaseMvpPresenter
import alex.parrotwings.presentations.mvp.views.BaseMvpView

/**
 * Created by alex on 21.07.2018.
 */
abstract class BasePresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {


    protected var mView: V? = null

    override fun attachView(mvpView: V) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }
}