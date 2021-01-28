package geekbarains.material.ui.marspicture

import com.google.gson.annotations.SerializedName

data class MarsResponseDataPhotos (
    @field:SerializedName("photos") val photos: List<MarsResponseData>
)