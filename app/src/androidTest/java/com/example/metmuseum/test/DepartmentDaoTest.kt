package com.example.metmuseum.test

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.DepartmentDb
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

@RunWith(AndroidJUnit4::class)
class DepartmentDaoTest {
    private lateinit var departmentDao: DepartmentDao
    private lateinit var departmentDb: DepartmentDb

    private var department1 = Department(1, "American Decorative Arts")
    private var department2 = Department(2, "Arms and Armor")

    // unility functions
    private suspend fun addOneDepartmentToDb() {
        departmentDao.insert(department1.asDbDepartment())
    }

    private suspend fun addTwoDepartmentsToDb() {
        departmentDao.insert(department1.asDbDepartment())
        departmentDao.insert(department2.asDbDepartment())
    }

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        departmentDb = Room.inMemoryDatabaseBuilder(context, DepartmentDb::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        departmentDao = departmentDb.departmentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        departmentDb.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInert_insertDepartmentIntoDB() = runBlocking {
        addOneDepartmentToDb()
        val allItems = departmentDao.getAllDepartments().first()
        assertEquals(allItems[0].asDomainDepartment(), department1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllDepartments_returnsAllDepartmentsFromDB() = runBlocking {
        addTwoDepartmentsToDb()
        val allItems = departmentDao.getAllDepartments().first()
        assertEquals(allItems[0].asDomainDepartment(), department1)
        assertEquals(allItems[1].asDomainDepartment(), department2)
    }
}