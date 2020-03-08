package ali.naveed.latestnytimes.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultResp(var status: String? = null,
    var copyright: String? = null,
    @SerializedName("num_results")
    var numResults: Int = 0,
    @SerializedName("results")
    var itemResults: MutableList<ItemResult>? = null):Parcelable

