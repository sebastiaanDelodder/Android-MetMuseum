package com.example.metmuseum.fake

import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.dbDepartment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A fake implementation of the [DepartmentDao] interface for testing purposes.
 *
 * This class provides in-memory storage for [dbDepartment] entities and overrides the methods of the
 * [DepartmentDao] interface to manipulate and retrieve data in a test environment.
 *
 * @constructor Creates a new instance of FakeDepartmentDao.
 */
class FakeDepartmentDao: DepartmentDao {

    /**
     * In-memory storage for [dbDepartment] entities.
     */
    private val departments = mutableListOf<dbDepartment>()

    /**
     * Inserts a new [dbDepartment] into the in-memory storage.
     *
     * @param item The [dbDepartment] to be inserted.
     */
    override suspend fun insert(item: dbDepartment) {
        departments.add(item)
    }

    /**
     * Updates an existing [dbDepartment] in the in-memory storage.
     *
     * @param item The [dbDepartment] to be updated.
     */
    override suspend fun update(item: dbDepartment) {
        departments.remove(item)
        departments.add(item)
    }

    /**
     * Deletes a [dbDepartment] from the in-memory storage.
     *
     * @param item The [dbDepartment] to be deleted.
     */
    override suspend fun delete(item: dbDepartment) {
        departments.remove(item)
    }

    /**
     * Retrieves a [dbDepartment] entity from the in-memory storage based on its departmentId.
     *
     * @param id The departmentId of the [dbDepartment] entity to retrieve.
     * @return A [Flow] emitting the [dbDepartment] entity with the specified departmentId, or a default [dbDepartment]
     *         with id 0 and an empty display name if not found.
     */
    override fun getDepartment(id: Int): Flow<dbDepartment> {
        return departments.find {
            it.departmentId == id
        }.let {
            if(it != null){
                flow {
                    emit(it)
                }
            } else {
                flow {
                    emit(dbDepartment(0, ""))
                }
            }
        }
    }

    /**
     * Retrieves all [dbDepartment] entities from the in-memory storage.
     *
     * @return A [Flow] emitting a list of all [dbDepartment] entities.
     */
    override fun getAllDepartments(): Flow<List<dbDepartment>> {
        return flow {
            emit(departments)
        }
    }
}