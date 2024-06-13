package org.d3if3056.assesment.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if3056.assesment.model.Skincare
import org.d3if3056.assesment.network.ApiSatus
import org.d3if3056.assesment.network.SkincareApi
import java.io.ByteArrayOutputStream

class KoleksiViewModel : ViewModel() {
    var data = mutableStateOf(emptyList<Skincare>())
        private set

    var status = MutableStateFlow(ApiSatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

//    init {
//        retrieveData()
//    }

    fun retrieveData(userId: String){
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiSatus.LOADING
            try {
                data.value = SkincareApi.service.getSkincare(userId)
                status.value = ApiSatus.SUCCESS
            } catch (e: Exception){
                Log.d("KoleksiViewModel", "Failure: ${e.message}")
                status.value = ApiSatus.FAILED
            }
        }
    }

    fun saveData(userId: String, namaSkincare: String, jenisSkincare: String, bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = SkincareApi.service.postSkincare(
                    userId,
                    namaSkincare.toRequestBody("text/plain".toMediaTypeOrNull()),
                    jenisSkincare.toRequestBody("text/plain".toMediaTypeOrNull()),
                    bitmap.toMultipartBody()
                )
                if (result.status == "success")
                    retrieveData(userId)
                else
                    throw Exception(result.message)
            } catch (e: Exception){
                Log.d("KoleksiViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part{
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)

        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(), 0, byteArray.size
        )
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody
        )
    }

    fun clearMessage() {errorMessage.value = null}
}