package ali.naveed.latestnytimes.quickhelper

import android.content.Context
import android.widget.Toast
/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
object AlertUtils {


    fun showToast(context: Context,textResourceId: Int) {
        showToast(context, context.getString(textResourceId))
    }

    fun showToast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }


    fun showSingleBtnAlert(activity: BaseActivity, message: String) {

    }

}
