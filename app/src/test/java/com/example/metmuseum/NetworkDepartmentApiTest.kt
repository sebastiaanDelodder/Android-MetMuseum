package com.example.metmuseum

import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.services.DepartmentApiService
import com.example.metmuseum.network.services.getDepartmentsAsFlow
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

/**
 * Unit tests for the [DepartmentApiService] using [MockWebServer] to simulate network responses.
 */
class NetworkDepartmentApiTest {
    private lateinit var departmentsApiService: DepartmentApiService
    private lateinit var mockWebServer: MockWebServer

    /**
     * Set up method called before each test to initialize [MockWebServer] and [DepartmentApiService].
     */
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        departmentsApiService =
            Retrofit.Builder()
                .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .baseUrl(mockWebServer.url("/"))
                .build()
                .create(DepartmentApiService::class.java)
    }

    /**
     * Tear down method called after each test to shut down [MockWebServer].
     */
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    /**
     * Test case to verify that [DepartmentApiService.getDepartments] returns a list of departments successfully.
     */
    @Test
    fun `getDepartments returns list successfully`() =
        runBlocking {
            val responseBody = """
                {
                    "departments": [
                        {
                            "departmentId": 1,
                            "displayName": "American Decorative Arts"
                        },
                        {
                            "departmentId": 3,
                            "displayName": "Ancient Near Eastern Art"
                        },
                        {
                            "departmentId": 4,
                            "displayName": "Arms and Armor"
                        }
                    ]
                }
            """.trimIndent()
            mockWebServer.enqueue(MockResponse().setBody(responseBody))

            val response = departmentsApiService.getDepartments()

            assertNotNull(response)
            assertEquals(3, response.departments.size)
            assertEquals(1, response.departments[0].departmentId)
            assertEquals("American Decorative Arts", response.departments[0].displayName)
        }

    /**
     * Test case to verify that [DepartmentApiService.getDepartmentsAsFlow] emits items successfully.
     */
    @Test
    fun `getDepartmentsAsFlow is emitting items succesfully`() =
        runBlocking {
            val responseBody = """
                {
                    "departments": [
                        {
                            "departmentId": 1,
                            "displayName": "American Decorative Arts"
                        },
                        {
                            "departmentId": 3,
                            "displayName": "Ancient Near Eastern Art"
                        },
                        {
                            "departmentId": 4,
                            "displayName": "Arms and Armor"
                        }
                    ]
                }
            """.trimIndent()
            mockWebServer.enqueue(MockResponse().setBody(responseBody))


            val result = departmentsApiService.getDepartmentsAsFlow().map { it.asDomainObjects() }.first()

            assertEquals(3, result.size)
            assertEquals(1, result[0].departmentId)
        }
}