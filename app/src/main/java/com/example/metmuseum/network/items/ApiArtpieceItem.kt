package com.example.metmuseum.network.items

import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.model.Department
import com.example.metmuseum.network.ApiArtpiece
import com.example.metmuseum.network.ApiDepartment
import com.example.metmuseum.network.asDomainObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

@Serializable
data class ApiArtpieceItem (
    val total: Int,
    val objectIDs: List<Int>
){}

