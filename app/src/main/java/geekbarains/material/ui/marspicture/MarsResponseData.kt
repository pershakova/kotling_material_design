package geekbarains.material.ui.marspicture

import com.google.gson.annotations.SerializedName

data class MarsResponseData(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("sol") val sol: Int?,
        @field:SerializedName("img_src") val url: String?
)