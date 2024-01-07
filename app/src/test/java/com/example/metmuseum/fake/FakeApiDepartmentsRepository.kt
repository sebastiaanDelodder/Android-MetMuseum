package com.example.metmuseum.fake

import android.util.Log
import com.example.metmuseum.data.database.departments.asDbDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartments
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.services.DepartmentApiService
import com.example.metmuseum.network.services.getDepartmentsAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.net.SocketTimeoutException

class FakeApiDepartmentsRepository(
    private val fakeDepartmentApiService: FakeDepartmentsApiService,
    private val fakeDepartmentDao: FakeDepartmentDao) : DepartmentsRepository {

    override fun getDepartments(): Flow<List<Department>> {
        return fakeDepartmentDao.getAllDepartments().map {
            it.asDomainDepartments()
        }.onEach {
            if(it.isEmpty()){
                refresh()
            }
        }
    }

    override fun getDepartment(id: Int): Flow<Department?> {
        return fakeDepartmentDao.getDepartment(id).map {
            it.asDomainDepartment()
        }
    }

    override suspend fun insertDepartment(department: Department) {
        fakeDepartmentDao.insert(department.asDbDepartment())
    }

    override suspend fun deleteDepartment(department: Department) {
        fakeDepartmentDao.delete(department.asDbDepartment())
    }

    override suspend fun updateDepartment(department: Department) {
        fakeDepartmentDao.update(department.asDbDepartment())
    }

    override suspend fun refresh() {
        fakeDepartmentApiService.getDepartmentsAsFlow().asDomainObjects().collect {
                value ->
            for(department in value) {
                insertDepartment(department)
            }
        }
    }
}