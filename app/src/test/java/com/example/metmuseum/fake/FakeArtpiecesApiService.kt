package com.example.metmuseum.fake

import com.example.metmuseum.network.ApiArtpiece
import com.example.metmuseum.network.items.ApiArtpieceItem
import com.example.metmuseum.network.services.ArtpieceApiService

class FakeArtpiecesApiService: ArtpieceApiService{
    override suspend fun getArtpieces(departmentId: Int): ApiArtpieceItem {
        return FakeDataSource.artpieceItem
    }

    override suspend fun getArtpiece(objectId: Int): ApiArtpiece {
        return FakeDataSource.artpieces.first()
    }
}