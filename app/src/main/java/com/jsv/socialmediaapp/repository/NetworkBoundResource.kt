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
                        saveCallResult(processResponse(response))
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromShaPref()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExecutors.mainThread().execute {
                        // Get data from Sharpref
                        result.addSource(loadFromShaPref()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(loadFromShaPref()) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun loadFromShaPref(): LiveData<ResultType>

    @MainThread
    protected abstract fun apiCall(): LiveData<ApiResponse<RequestType>>
}