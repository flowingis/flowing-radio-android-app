package it.flowing.flowingradio

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import it.flowing.flowingradio.models.ApiResult
import it.flowing.flowingradio.models.Episode
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.spreaker.com/v2/shows/flowing-radio/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FlowingApiService {
    @GET("episodes")
    fun list(): Deferred<ApiResult>
}

object PodcastRepository {
    private val retrofitService: FlowingApiService by lazy {
        retrofit.create(FlowingApiService::class.java)
    }

    fun list(): Deferred<List<Episode>> =  GlobalScope.async { retrofitService.list().await().response.items }
}