package com.example.metmuseum.fake

import com.example.metmuseum.network.items.ApiDepartmentItem
import com.example.metmuseum.network.services.DepartmentApiService

/**
 * Fake implementation of the [DepartmentApiService] for testing purposes.
 *
 * This class provides a fake implementation of the API service to simulate the behavior of fetching
 * department data from a remote API. It returns a predefined [ApiDepartmentItem] from the [FakeDataSource]
 * for testing scenarios.
 *
 * @see DepartmentApiService
 * @see ApiDepartmentItem
 * @see FakeDataSource
 */
class FakeDepartmentsApiService : DepartmentApiService {

    /**
     * Retrieves a fake [ApiDepartmentItem] representing department data.
     *
     * This method simulates fetching department data from a remote API by returning a predefined
     * [ApiDepartmentItem] from the [FakeDataSource].
     *
     * @return A fake [ApiDepartmentItem] containing department data.
     */
    override suspend fun getDepartments(): ApiDepartmentItem {
        return FakeDataSource.departments
    }
}