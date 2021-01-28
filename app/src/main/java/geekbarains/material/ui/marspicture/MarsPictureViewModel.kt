package geekbarains.material.ui.marspicture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekbarains.material.BuildConfig
import geekbarains.material.ui.picture.PODRetrofitImpl
import geekbarains.material.ui.picture.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsPictureViewModel (private val liveDataForViewToObserve: MutableLiveData<MarsData> = MutableLiveData(),
private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()) :
        ViewModel() {

    fun getData(): LiveData<MarsData> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    private fun sendServerRequest() {
        liveDataForViewToObserve.value = MarsData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            MarsData.Error(Throwable("You need API key"))
        } else {
            retrofitImpl.getRetrofitImpl().getMarsPicture(apiKey, 1000, "fhaz").enqueue(object :
                    Callback<MarsResponseDataPhotos> {
                override fun onResponse(
                        call: Call<MarsResponseDataPhotos>,
                        response: Response<MarsResponseDataPhotos>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        liveDataForViewToObserve.value =
                                MarsData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        if (message.isNullOrEmpty()) {
                            liveDataForViewToObserve.value =
                                    MarsData.Error(Throwable("Unidentified error"))
                        } else {
                            liveDataForViewToObserve.value =
                                    MarsData.Error(Throwable(message))
                        }
                    }
                }

                override fun onFailure(call: Call<MarsResponseDataPhotos>, t: Throwable) {
                    liveDataForViewToObserve.value = MarsData.Error(t)
                }
            })
        }
    }
}