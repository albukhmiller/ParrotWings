package alex.parrotwings.presentations.mvp.presenters

import alex.parrotwings.presentations.mvp.views.BaseMvpView

/**
 * Created by alex on 21.07.2018.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(mvpView: V)
    fun detachView()
}