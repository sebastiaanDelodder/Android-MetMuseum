package com.example.metmuseum.fake

import com.example.metmuseum.data.database.departments.asDbDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartments
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.services.getDepartmentsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

/**
 * Fake implementation of [DepartmentsRepository] for testing purposes.
 *
 * @property fakeDepartmentApiService The fake implementation of the API service for fetching departments.
 * @property fakeDepartmentDao The fake implementation of the DAO for database operations.
 */
class FakeApiDepartmentsRepository(
    private val fakeDepartmentApiService: FakeDepartmentsApiService,
    private val fakeDepartmentDao: FakeDepartmentDao) : DepartmentsRepository {

    /**
     * Retrieves a flow of all departments. If the local database is empty, triggers a refresh to fetch from the API.
     *
     * @return A [Flow] emitting a list of [Department] objects.
     */
    override fun getDepartments(): Flow<List<Department>> {
        return fakeDepartmentDao.getAllDepartments().map {
            it.asDomainDepartments()
        }.onEach {
            if(it.isEmpty()){
                refresh()
            }
        }
    }

    /**
     * Retrieves a flow of a specific department based on its ID.
     *
     * @param id The ID of the department to retrieve.
     * @return A [Flow] emitting a [Department] object or null if not found.
     */
    override fun getDepartment(id: Int): Flow<Department?> {
        return fakeDepartmentDao.getDepartment(id).map {
            it.asDomainDepartment()
        }
    }

    /**
     * Inserts a new department into the local database.
     *
     * @param department The [Department] to be inserted.
     */
    override suspend fun insertDepartment(department: Department) {
        fakeDepartmentDao.insert(department.asDbDepartment())
    }

    /**
     * Deletes a department from the local database.
     *
     * @param department The [Department] to be deleted.
     */
    override suspend fun deleteDepartment(department: Department) {
        fakeDepartmentDao.delete(department.asDbDepartment())
    }

    /**
     * Updates an existing department in the local database.
     *
     * @param department The updated [Department] object.
     */
    override suspend fun updateDepartment(department: Department) {
        fakeDepartmentDao.update(department.asDbDepartment())
    }

    /**
     * Refreshes the local database by fetching departments from the fake API service and inserting them.
     */
    override suspend fun refresh() {
        fakeDepartmentApiService.getDepartmentsAsFlow().asDomainObjects().collect {
                value ->
            for(department in value) {
                insertDepartment(department)
            }
        }
    }
}