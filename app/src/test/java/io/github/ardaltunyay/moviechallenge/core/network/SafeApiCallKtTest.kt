package io.github.ardaltunyay.moviechallenge.core.network

import com.google.gson.Gson
import io.github.ardaltunyay.moviechallenge.core.exceptions.ServerException
import io.github.ardaltunyay.moviechallenge.core.exceptions.TokenException
import io.github.ardaltunyay.moviechallenge.data.remote.model.base.BaseError
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class SafeApiCallKtTest {
    object NetworkObjectForTesting {
        suspend fun <T : Any> getResponse(): Response<T> {
            return Response.success(null)
        }
    }

    private lateinit var networkObject: NetworkObjectForTesting

    @Before
    fun setup() {
        networkObject = mockk(relaxed = true)
    }

    @Test
    fun `should return result success when response code is 200`() = runBlockingTest {
        coEvery { networkObject.getResponse<String>() } returns Response.success("Success")

        val sut = safeApiCall { networkObject.getResponse<String>() }

        sut.shouldBeTypeOf<Result.Success<String>>()
    }

    @Test
    fun `should return result error when throw exception`() = runBlockingTest {

        val success = Response.success("for testing")

        val sut = safeApiCall {
            throw Exception("Error!!!")
            success
        }

        sut.shouldBeTypeOf<Result.Error>()
    }

    /**
     * FIXME Coroutine dispatcher io issue
     * java.lang.IllegalStateException: This job has not completed yet
     * 	at kotlinx.coroutines.JobSupport.getCompletionExceptionOrNull(JobSupport.kt:1128)
     *
     * 	@link https://github.com/Kotlin/kotlinx.coroutines/issues/1204
     * 	Temporary solution: runBlocking instead of runBlockingTest
     */
    @Test
    fun `should return result error when response code is 400`() {
        runBlocking {
            coEvery { networkObject.getResponse<String>() } returns Response.error(
                400,
                "Bad Request".toResponseBody()
            )

            val sut = safeApiCall { networkObject.getResponse<String>() }

            sut.shouldBeTypeOf<Result.Error>()
        }
    }

    /**
     * FIXME Coroutine dispatcher io issue
     * java.lang.IllegalStateException: This job has not completed yet
     * 	at kotlinx.coroutines.JobSupport.getCompletionExceptionOrNull(JobSupport.kt:1128)
     *
     * 	@link https://github.com/Kotlin/kotlinx.coroutines/issues/1204
     * 	Temporary solution: runBlocking instead of runBlockingTest
     */
    @Test
    fun `should return token exception in result error when response code is 401`() {
        runBlocking {
            val statusMessage = "Token error!!!"
            val baseErrorStr = Gson().toJson(BaseError(status_message = statusMessage))

            coEvery { networkObject.getResponse<String>() } returns Response.error(
                401,
                baseErrorStr.toResponseBody()
            )

            val sut = safeApiCall { networkObject.getResponse<String>() }

            sut.shouldBeTypeOf<Result.Error>()
            sut.exception.shouldBeTypeOf<TokenException>()
            sut.exception.message shouldBe statusMessage
        }
    }

    /**
     * FIXME Coroutine dispatcher io issue
     * java.lang.IllegalStateException: This job has not completed yet
     * 	at kotlinx.coroutines.JobSupport.getCompletionExceptionOrNull(JobSupport.kt:1128)
     *
     * 	@link https://github.com/Kotlin/kotlinx.coroutines/issues/1204
     * 	Temporary solution: runBlocking instead of runBlockingTest
     */
    @Test
    fun `should return server exception in result error when response code is 500`() {
        runBlocking {
            coEvery { networkObject.getResponse<String>() } returns Response.error(
                500,
                "Server error".toResponseBody()
            )

            val sut = safeApiCall { networkObject.getResponse<String>() }

            sut.shouldBeTypeOf<Result.Error>()
            sut.exception.shouldBeTypeOf<ServerException>()
        }
    }
}