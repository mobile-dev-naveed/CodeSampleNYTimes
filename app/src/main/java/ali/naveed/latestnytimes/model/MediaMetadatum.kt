package ali.naveed.latestnytimes.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaMetadatum(

    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("format")
    @Expose
    var format: String? = null,
    @SerializedName("height")
    @Expose
    var height: Int? = null,
    @SerializedName("width")
    @Expose
    var width: Int? = null):Parcelable
