package com.example.metmuseum.fake

import android.util.Log
import com.example.metmuseum.network.ApiDepartment
import com.example.metmuseum.network.items.ApiDepartmentItem
import com.example.metmuseum.network.services.DepartmentApiService

class FakeDepartmentsApiService : DepartmentApiService {
    override suspend fun getDepartments(): ApiDepartmentItem {
        Log.i("FakeDepartmentsApiService", "TEST1")
        return FakeDataSource.departments
    }
}