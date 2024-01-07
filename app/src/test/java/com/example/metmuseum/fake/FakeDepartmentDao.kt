package com.example.metmuseum.fake

import com.example.metmuseum.data.database.departments.DepartmentDao
import com.example.metmuseum.data.database.departments.dbDepartment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDepartmentDao: DepartmentDao {

    private val departments = mutableListOf<dbDepartment>()
    override suspend fun insert(item: dbDepartment) {
        departments.add(item)
    }

    override suspend fun update(item: dbDepartment) {
        departments.remove(item)
        departments.add(item)
    }

    override suspend fun delete(item: dbDepartment) {
        departments.remove(item)
    }

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

    override fun getAllDepartments(): Flow<List<dbDepartment>> {
        return flow {
            emit(departments)
        }
    }
}