package com.example.metmuseum.data.repository

import android.util.Log
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.asDbDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartments
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.services.DepartmentApiService
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.services.getDepartmentsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

/**
 * Repository interface for managing [Department] entities.
 *
 * This interface defines methods for retrieving, inserting, updating, deleting, and refreshing Departments.
 */
interface DepartmentsRepository {
    /**
     * Retrieve all Departments from the given data source.
     *
     * @return A [Flow] emitting a list of [Department] objects.
     */
    fun getDepartments(): Flow<List<Department>>

    /**
     * Retrieve a [Department] from the given data source that matches the specified [id].
     *
     * @param id The unique identifier of the [Department] to retrieve.
     * @return A [Flow] emitting the [Department] with the specified [id], or null if not found.
     */
    fun getDepartment(id: Int): Flow<Department?>

    /**
     * Insert a new [Department] into the local database.
     *
     * @param department The [Department] to be inserted.
     */
    suspend fun insertDepartment(department: Department)

    /**
     * Delete a [Department] from the local database.
     *
     * @param department The [Department] to be deleted.
     */
    suspend fun deleteDepartment(department: Department)

    /**
     * Update an existing [Department] in the local database.
     *
     * @param department The [Department] to be updated.
     */
    suspend fun updateDepartment(department: Department)

    /**
     * Refresh the departments stored in the data source.
     */
    suspend fun refresh()
}

/**
 * Implementation of the [DepartmentsRepository] interface with caching functionality.
 *
 * This class uses a local database access object ([DepartmentDao]) and a remote API service ([DepartmentApiService]) to manage Departments.
 *
 * @property departmentDao Local database access object for Departments.
 * @property departmentApiService Remote API service for Departments.
 */
class CachingDepartmentsRepository(
    private val departmentDao: DepartmentDao,
    private val departmentApiService: DepartmentApiService
): DepartmentsRepository {

    /**
     * Retrieve all Departments from the given data source.
     *
     * The flow is observed for changes, and if no Departments are cached, a refresh is triggered.
     *
     * @return A [Flow] emitting a list of [Department] objects.
     */
    override fun getDepartments(): Flow<List<Department>> {
        Log.i("CachingDepartmentsRepository", "getDepartments")
        return departmentDao.getAllDepartments().map {
            it.asDomainDepartments()
        }.onEach {
            if(it.isEmpty()){
                refresh()
            }
        }
    }

    /**
     * Retrieve a [Department] from the given data source that matches the specified [id].
     *
     * @param id The ID of the [Department] to retrieve.
     * @return A [Flow] emitting the [Department] with the specified ID, or null if not found.
     */
    override fun getDepartment(id: Int): Flow<Department?> {
        return departmentDao.getDepartment(id).map {
            it.asDomainDepartment()
        }
    }

    /**
     * Inserts a new [Department] into the local database.
     *
     * @param department The [Department] to be inserted.
     */
    override suspend fun insertDepartment(department: Department) {
        departmentDao.insert(department.asDbDepartment())
    }

    /**
     * Deletes a [Department] from the local database.
     *
     * @param department The [Department] to be deleted.
     */
    override suspend fun deleteDepartment(department: Department) {
        departmentDao.delete(department.asDbDepartment())
    }

    /**
     * Updates an existing [Department] in the local database.
     *
     * @param department The [Department] to be updated.
     */
    override suspend fun updateDepartment(department: Department) {
        departmentDao.update(department.asDbDepartment())
    }

    /**
     * Initiates a refresh of a specific [Department] data.
     *
     * The refresh is performed by querying a remote API for the specific Department with the given object ID.
     *
     * @param objectId The object ID of the [Department] to refresh.
     */
    override suspend fun refresh(){
        try {
            departmentApiService.getDepartmentsAsFlow().asDomainObjects().collect {
                    value ->
                Log.i("CachingDepartmentsRepository", "Refresh: $value")
                for(department in value) {
                    insertDepartment(department)
                }
            }
        }
        catch(e: SocketTimeoutException){
            Log.e("CachingDepartmentsRepository", "Refresh: Error: " +e.stackTraceToString(), )
        }
    }
}