package im.syf.devbyte.data.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DevByteApi {

    companion object {

        private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
