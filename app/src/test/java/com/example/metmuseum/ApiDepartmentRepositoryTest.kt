package com.example.metmuseum

import android.util.Log
import com.example.metmuseum.fake.FakeApiDepartmentsRepository
import com.example.metmuseum.fake.FakeDataSource
import com.example.metmuseum.fake.FakeDepartmentDao
import com.example.metmuseum.fake.FakeDepartmentsApiService
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObjects
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
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
    private var fakeDepartmentDao = FakeDepartmentDao()

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

    @Test
    fun fakeApiDepartmentsRepository_getDepartment_verifyDepartmentById() =
        runBlocking {
            // Create an instance of the fake repository
            val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(fakeDepartmentsApiService)

            // Get a department by ID from the repository
            val departmentId = 1
            val resultDepartment = fakeApiDepartmentsRepository.getDepartment(departmentId).first()

            // Verify the result against the expected department
            assertEquals(
                FakeDataSource.departments.departments.firstOrNull { it.departmentId == departmentId },
                resultDepartment
            )
        }

    @Test
    fun fakeApiDepartmentsRepository_insertDepartment_verifyInsertedDepartment() =
        runBlocking {
            // Create an instance of the fake repository
            val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(fakeDepartmentsApiService)

            // Create a new department to insert
            val newDepartment = Department(departmentId = 10, displayName = "New Department")

            // Insert the department into the repository
            fakeApiDepartmentsRepository.insertDepartment(newDepartment)

            // Verify that the inserted department can be retrieved
            val resultDepartment = fakeApiDepartmentsRepository.getDepartment(newDepartment.departmentId).first()

            // Verify the result against the inserted department
            assertEquals(newDepartment, resultDepartment)
        }

    @Test
    fun fakeApiDepartmentsRepository_deleteDepartment_verifyDeletedDepartment() =
        runBlocking {
            // Create an instance of the fake repository
            val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(fakeDepartmentsApiService)

            // Create a department to delete
            val departmentToDelete = FakeDataSource.departments.departments.asDomainObjects().first()

            // Delete the department from the repository
            fakeApiDepartmentsRepository.deleteDepartment(departmentToDelete)

            // Verify that the deleted department cannot be retrieved
            val resultDepartment = fakeApiDepartmentsRepository.getDepartment(departmentToDelete.departmentId).first()

            // Verify the result against null (department not found)
            assertEquals(null, resultDepartment)
        }

    @Test
    fun fakeApiDepartmentsRepository_updateDepartment_verifyUpdatedDepartment() =
        runBlocking {
            // Create an instance of the fake repository
            val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(fakeDepartmentsApiService)

            // Create a department to update
            val departmentToUpdate = FakeDataSource.departments.departments.asDomainObjects().first()
            val updatedDepartment = departmentToUpdate.copy(displayName = "Updated Department")

            // Update the department in the repository
            fakeApiDepartmentsRepository.updateDepartment(updatedDepartment)

            // Verify that the updated department can be retrieved
            val resultDepartment = fakeApiDepartmentsRepository.getDepartment(departmentToUpdate.departmentId).first()

            // Verify the result against the updated department
            assertEquals(updatedDepartment, resultDepartment)
        }

    @Test
    fun fakeApiDepartmentsRepository_refresh_doesNotThrowException() =
        runBlocking {
            // Create an instance of the fake repository
            val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(fakeDepartmentsApiService)

            // Call the refresh method and ensure it does not throw an exception
            fakeApiDepartmentsRepository.refresh()
        }
}