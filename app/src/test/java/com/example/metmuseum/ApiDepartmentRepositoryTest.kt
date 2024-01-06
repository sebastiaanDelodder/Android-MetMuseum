package com.example.metmuseum

import android.util.Log
import com.example.metmuseum.fake.FakeApiDepartmentsRepository
import com.example.metmuseum.fake.FakeDataSource
import com.example.metmuseum.fake.FakeDepartmentsApiService
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObjects
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.any
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.`when`

class ApiDepartmentRepositoryTest {

    private var fakeDepartmentsApiService = FakeDepartmentsApiService()

    @Test
    fun apiDepartmentRepository_getDepartments_verifyDepartmentsList() =
        runBlocking {
            // Create an instance of the fake repository
            val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(fakeDepartmentsApiService)

            // Collect the flow of departments from the repository
            val resultDepartmentsList = mutableListOf<List<Department>>()
            fakeApiDepartmentsRepository.getDepartments().collect { departments ->
                resultDepartmentsList.add(departments)
            }


            // Verify the result against the expected departments list
            assertEquals(
                FakeDataSource.departments.departments.asDomainObjects(),
                resultDepartmentsList.first()
            )
        }
}