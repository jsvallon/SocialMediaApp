package com.jsv.socialmediaapp.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.jsv.socialmediaapp.AppExecutors
import com.jsv.socialmediaapp.service.ApiEmptyResponse
import com.jsv.socialmediaapp.service.ApiErrorResponse
import com.jsv.socialmediaapp.service.ApiResponse
import com.jsv.socialmediaapp.service.ApiSuccessResponse
import com.jsv.socialmediaapp.vo.Resource


abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = apiCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    appExecutors.disk().execute {
                        processResponse(response)
                        appExecutors.mainThread().execute {
                            result.addSource(convertItem(response.body)) { newData ->
                               setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {

                        // reload from disk whatever we had
//                        result.addSource(loadFromDb()) { newData ->
                           // setValue(Resource.success(newData))
//                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
//                    result.addSource(convertItem(LiveData<ResultType>())) { newData ->
//                        setValue(Resource.error(response.errorMessage, newData))
//                    }
                    //setValue(Resource.error(response.errorMessage, newData))
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    protected abstract fun convertItem(item: RequestType): LiveData<ResultType>

    @MainThread
    protected abstract fun apiCall(): LiveData<ApiResponse<RequestType>>
}