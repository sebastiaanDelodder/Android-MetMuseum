package com.example.metmuseum.fake

import android.util.Log
import com.example.metmuseum.data.repository.DepartmentsRepository
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.network.services.DepartmentApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeApiDepartmentsRepository(
    private val fakeDepartmentApiService: FakeDepartmentsApiService) : DepartmentsRepository {
    override fun getDepartments(): Flow<List<Department>> {
        Log.i("FakeApiDepartmentsRepository", "TEST2")
        return flow {
            emit(fakeDepartmentApiService.getDepartments().departments.asDomainObjects())
        }
    }

    override fun getDepartment(id: Int): Flow<Department?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertDepartment(department: Department) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDepartment(department: Department) {
        TODO("Not yet implemented")
    }

    override suspend fun updateDepartment(department: Department) {
        TODO("Not yet implemented")
    }

    override suspend fun refresh() {
        //do nothing
    }

}