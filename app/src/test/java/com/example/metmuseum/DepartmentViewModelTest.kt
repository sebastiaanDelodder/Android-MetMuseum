package com.example.metmuseum

import com.example.metmuseum.fake.FakeApiDepartmentsRepository
import com.example.metmuseum.fake.FakeDataSource
import com.example.metmuseum.fake.FakeDepartmentDao
import com.example.metmuseum.fake.FakeDepartmentsApiService
import com.example.metmuseum.network.asDomainObjects
import com.example.metmuseum.ui.artScreen.state.DepartmentApiState
import com.example.metmuseum.ui.artScreen.viewModels.DepartmentViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Unit tests for the [DepartmentViewModel] class, testing its interaction with the repository.
 */
class DepartmentViewModelTest {

    /**
     * A [TestDispatcherRule] to provide a test dispatcher for coroutine testing.
     */
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /**
     * The [DepartmentViewModel] under test.
     */
    private lateinit var viewModel: DepartmentViewModel

    /**
     * A [FakeApiDepartmentsRepository] for mocking the repository in the test.
     */
    private lateinit var fakeRepository: FakeApiDepartmentsRepository

    /**
     * Setup method executed before each test case to initialize the test environment.
     */
    @Before
    fun setup() {
        // Mock the repository
        fakeRepository = FakeApiDepartmentsRepository(
            fakeDepartmentApiService = FakeDepartmentsApiService(),
            fakeDepartmentDao = FakeDepartmentDao()
        )
        viewModel = DepartmentViewModel(fakeRepository)
        Thread.sleep(5000)
    }

    /**
     * Test case to verify that [DepartmentViewModel.getRepoDepartments] updates [DepartmentViewModel.uiListState] and
     * [DepartmentViewModel.departmentApiState] on a successful API call.
     */
    @Test
    fun `getRepoDepartments() should update uiListState and departmentApiState on success`() =
        runBlocking {
            // Assert
            assertEquals(viewModel.departmentApiState, DepartmentApiState.Success)
            assertEquals(
                FakeDataSource.departments.departments.asDomainObjects(),
                viewModel.uiListState.value.departments
            )
        }
}


/**
 * A JUnit Test Rule that provides a [TestDispatcher] for coroutine testing.
 *
 * This rule sets up and tears down the main dispatcher for testing purposes.
 *
 * @param testDispatcher The [TestDispatcher] to be used during testing. Default is [UnconfinedTestDispatcher].
 */
class TestDispatcherRule @OptIn(ExperimentalCoroutinesApi::class) constructor(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    /**
     * Overrides the starting method to set the main dispatcher to the provided [testDispatcher].
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Overrides the finished method to reset the main dispatcher.
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}