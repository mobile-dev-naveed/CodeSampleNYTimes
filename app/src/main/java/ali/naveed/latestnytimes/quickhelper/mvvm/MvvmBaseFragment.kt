package ali.naveed.latestnytimes.quickhelper.mvvm

import ali.naveed.latestnytimes.quickhelper.BaseFragment

/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
open abstract class MvvmBaseFragment<T> : BaseFragment() {

    var viewModel: T? = null

    abstract fun registerVM()

}
