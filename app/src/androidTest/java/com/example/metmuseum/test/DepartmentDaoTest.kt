package com.example.metmuseum.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.metmuseum.data.database.MetArtDb
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.asDbDepartment
import com.example.metmuseum.data.database.departments.asDomainDepartment
import com.example.metmuseum.model.Department
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test class for testing the functionality of [DepartmentDao].
 *
 * This test class includes various test cases for CRUD operations on the [DepartmentDao].
 *
 * @see DepartmentDao
 */
@RunWith(AndroidJUnit4::class)
class DepartmentDaoTest {
    private lateinit var departmentDao: DepartmentDao
    private lateinit var departmentDb: MetArtDb

    private var department1 = Department(1, "American Decorative Arts")
    private var department2 = Department(2, "Arms and Armor")


    /**
     * Adds a single department to the in-memory database.
     */
    private suspend fun addOneDepartmentToDb() {
        departmentDao.insert(department1.asDbDepartment())
    }

    /**
     * Adds two departments to the in-memory database.
     */
    private suspend fun addTwoDepartmentsToDb() {
        departmentDao.insert(department1.asDbDepartment())
        departmentDao.insert(department2.asDbDepartment())
    }

    /**
     * Sets up the in-memory database and initializes the [DepartmentDao] before each test.
     */
    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        departmentDb = Room.inMemoryDatabaseBuilder(context, MetArtDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        departmentDao = departmentDb.departmentDao()
    }

    /**
     * Closes the in-memory database after each test.
     */
    @After
    @Throws(IOException::class)
    fun closeDb() {
        departmentDb.close()
    }

    /**
     * Test case to verify the insertion of a department into the database.
     */
    @Test
    fun daoInert_insertDepartmentIntoDB() = runBlocking {
        addOneDepartmentToDb()
        val allItems = departmentDao.getAllDepartments().first()
        assertEquals(allItems[0].asDomainDepartment(), department1)
    }

    /**
     * Test case to verify the retrieval of all departments from the database.
     */
    @Test
    fun daoGetAllDepartments_returnsAllDepartmentsFromDB() = runBlocking {
        addTwoDepartmentsToDb()
        val allItems = departmentDao.getAllDepartments().first()
        assertEquals(allItems[0].asDomainDepartment(), department1)
        assertEquals(allItems[1].asDomainDepartment(), department2)
    }

    /**
     * Test case to verify the retrieval of a department by ID from the database.
     */
    @Test
    fun daoGetDepartmentById_returnsDepartmentFromDB() = runBlocking {
        addTwoDepartmentsToDb()
        val department = departmentDao.getDepartment(1).first()
        assertEquals(department1, department.asDomainDepartment())
    }

    /**
     * Test case to verify the update of a department in the database.
     */
    @Test
    fun daoUpdateDepartment_updatesDepartmentInDB() = runBlocking {
        addTwoDepartmentsToDb()
        val department = departmentDao.getDepartment(1).first()
        assertEquals(department1, department.asDomainDepartment())

        //Update
        val updatedDepartment = department.copy(displayName = "New Name")
        departmentDao.update(updatedDepartment)

        //Verify
        val departmentUpdated = departmentDao.getDepartment(1).first()
        assertEquals(updatedDepartment, departmentUpdated)
    }

    /**
     * Test case to verify the deletion of a department from the database.
     */
    @Test
    fun daoDeleteDepartment_deletesDepartmentFromDB() = runBlocking {
        addTwoDepartmentsToDb()
        val department = departmentDao.getDepartment(1).first()
        assertEquals(department1, department.asDomainDepartment())

        //Delete
        departmentDao.delete(department)

        //Verify
        val departmentDeleted = departmentDao.getDepartment(1).first()
        assertEquals(null, departmentDeleted)
    }
}