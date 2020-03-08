package ali.naveed.latestnytimes.quickhelper.mvvm

import ali.naveed.latestnytimes.quickhelper.BaseActivity

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
open class MvvmBaseActivity<T> : BaseActivity() {

    var viewModel: T? = null

    open fun registerObservers(){}


}
