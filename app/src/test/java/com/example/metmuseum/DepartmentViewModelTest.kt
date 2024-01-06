package com.example.metmuseum

import com.example.metmuseum.fake.FakeApiDepartmentsRepository
import com.example.metmuseum.fake.FakeDataSource
import com.example.metmuseum.fake.FakeDepartmentsApiService
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.ui.artScreen.viewModels.DepartmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.TimeUnit

class DepartmentViewModelTest {

    val fakeDepartments = FakeDataSource.departments.departments.asDomainObjects()

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun gettingDepartmentsInitialize() {
        val fakeApiDepartmentsRepository = FakeApiDepartmentsRepository(
            fakeDepartmentApiService = FakeDepartmentsApiService()
        )
        val viewModel = DepartmentViewModel(
            departmentsRepository = fakeApiDepartmentsRepository,
        )

        //val resultDepartmentsList = mutableListOf<List<Department>>()
        //viewModel.setNewTaskName(someTaskName)
        Assert.assertEquals(fakeDepartments, viewModel.uiListState.value.departments)
    }
}

class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}