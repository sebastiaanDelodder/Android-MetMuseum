package com.example.metmuseum.data.repository

import android.util.Log
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.asDbDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartments
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.DepartmentApiService
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.getDepartmentsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

/**
 * Repository that provides insert, update, delete, and retrieve of [Department] from a given data source.
 */
interface DepartmentsRepository {
    /**
     * Retrieve all the departments from the the given data source.
     */
    fun getDepartments(): Flow<List<Department>>

    /**
     * Retrieve a department from the given data source that matches with the [id].
     */
    fun getDepartment(id: Int): Flow<Department?>

    /**
     * Insert department in the data source
     */
    suspend fun insertDepartment(department: Department)

    /**
     * Delete department from the data source
     */
    suspend fun deleteDepartment(department: Department)

    /**
     * Update department in the data source
     */
    suspend fun updateDepartment(department: Department)

    /**
     * Refresh the departments stored in the data source
     */
    suspend fun refresh()
}

class CachingDepartmentsRepository(
    private val departmentDao: DepartmentDao,
    private val departmentApiService: DepartmentApiService
): DepartmentsRepository {

    //this repo contains logic to refresh the departments (remote)
    //sometimes that logic is written in a 'usecase'
    override fun getDepartments(): Flow<List<Department>> {
        return departmentDao.getAllDepartments().map {
            it.asDomainDepartments()
        }.onEach {
            //todo: check when refresh is called (why duplicates??)
            if(it.isEmpty()){
                refresh()
            }
        }
    }

    override fun getDepartment(id: Int): Flow<Department?> {
        return departmentDao.getDepartment(id).map {
            it.asDomainDepartment()
        }
    }

    override suspend fun insertDepartment(department: Department) {
        departmentDao.insert(department.asDbDepartment())
    }

    override suspend fun deleteDepartment(department: Department) {
        departmentDao.delete(department.asDbDepartment())
    }

    override suspend fun updateDepartment(department: Department) {
        departmentDao.update(department.asDbDepartment())
    }

    override suspend fun refresh(){
        try {
            departmentApiService.getDepartmentsAsFlow().asDomainObjects().collect {
                    value ->
                Log.i("CachingDepartmentsRepository", "refresh: $value")
                for(department in value) {
                    Log.i("CachingDepartmentsRepository", "refresh: $department")
                    insertDepartment(department)
                }

            }
        }
        catch(e: SocketTimeoutException){
            //log something
            //TODO
            Log.e("API", "refresh: "+e.stackTraceToString(), )
        }

    }
}