package com.mohdroid.domain.network.common

import com.google.gson.JsonSyntaxException
import com.mohdroid.domain.enums.ErrorType
import com.mohdroid.domain.result.Error
import com.mohdroid.domain.result.ServiceResult
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.io.IOException


interface BaseService {
    suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): ServiceResult<T> {
        return try {
            val response = call.invoke()
            when {
                response.code() == 200 -> ServiceResult.Success(response.body())
                response.code() == 401 -> ServiceResult.Failure(Error(ErrorType.INVALID_TOKEN_EXCEPTION))
                else -> ServiceResult.Failure(
                    Error(
                        ErrorType.EXCEPTION,
                        getErrorMessage(response.errorBody())
                    )
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
            ServiceResult.Failure(Error(ErrorType.IO_EXCEPTION))

        } catch (e: EOFException) {
            e.printStackTrace()
            ServiceResult.Failure(Error(ErrorType.EOF_EXCEPTION))
        } catch (e: HttpException) {
            e.printStackTrace()
            ServiceResult.Failure(Error(ErrorType.HTTP_EXCEPTION))
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            ServiceResult.Failure(Error(ErrorType.JSON_SYNTAX_EXCEPTION))
        } catch (e: Exception) {
            e.printStackTrace()
            ServiceResult.Failure(Error(ErrorType.EXCEPTION))
        }
    }

    private fun getErrorMessage(errorBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(errorBody?.string() ?: "")
            jsonObject.getString("message")
        } catch (e: Exception) {
            ""
        }
    }
}


