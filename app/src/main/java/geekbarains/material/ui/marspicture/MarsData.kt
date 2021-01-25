package geekbarains.material.ui.marspicture

sealed class MarsData {
    data class Success(val serverResponseData: MarsResponseDataPhotos) : MarsData()
    data class Error(val error: Throwable) : MarsData()
    data class Loading(val progress: Int?) : MarsData()
}