package com.example.metmuseum.fake

import com.example.metmuseum.network.ApiArtpiece
import com.example.metmuseum.network.ApiDepartment
import com.example.metmuseum.network.items.ApiArtpieceItem
import com.example.metmuseum.network.items.ApiConstituent
import com.example.metmuseum.network.items.ApiDepartmentItem
import com.example.metmuseum.network.items.ApiElementMeasurements
import com.example.metmuseum.network.items.ApiMeasurement
import com.example.metmuseum.network.items.ApiTag

/**
 * Fake data source containing mock data for testing purposes.
 *
 * This object provides predefined values for department IDs, names, and mock art piece information.
 */
object FakeDataSource {

    /**
     * Constant representing the first department ID.
     */
    const val departmentIdOne = 1
    /**
     * Constant representing the name of the first department.
     */
    const val departmentNameOne = "American Decorative Arts"

    /**
     * Constant representing the second department ID.
     */
    const val departmentIdTwo = 2
    /**
     * Constant representing the name of the second department.
     */
    const val departmentNameTwo = "Arms and Armor"

    /**
     * Constant representing the third department ID.
     */
    const val departmentIdThree = 3
    /**
     * Constant representing the name of the third department.
     */
    const val departmentNameThree = "Arts of Africa, Oceania, and the Americas"

    /**
     * ApiDepartmentItem containing mock data for departments.
     */
    val departments = ApiDepartmentItem(
        departments = listOf(
            ApiDepartment(departmentIdOne, departmentNameOne),
            ApiDepartment(departmentIdTwo, departmentNameTwo),
            ApiDepartment(departmentIdThree, departmentNameThree),
        )
    )

    /**
     * ApiArtpieceItem containing mock data for an art piece.
     */
    val artpieceItem =
        ApiArtpieceItem(
            total = 1,
            objectIDs = listOf(1),
        )

    /**
     * List containing mock data for art pieces.
     */
    val artpieces = listOf(
        ApiArtpiece(
            department = departmentNameOne,
            title = "Arms and Armor",
            primaryImage = "https://images.metmuseum.org/CRDImages/aa/original/DT159.jpg",
            objectDate = "ca. 1500",
            objectURL = "https://www.metmuseum.org/art/collection/search/23953",
            artistDisplayName = "German",
            artistDisplayBio = "German, Augsburg",
            artistNationality = "German",
            artistBeginDate = "active 1480–1530",
            accessionNumber = "36.25.107",
            medium = "Steel, gold, leather",
            dimensions = "H. 17 1/2 in. (44.5 cm); W. 9 1/2 in. (24.1 cm); D. 9 1/2 in. (24.1 cm); Wt. 7 lb. 8 oz. (3402 g)",
            creditLine = "Gift of William H. Riggs, 1913",
            objectID = 1,
            accessionYear = "1913",
            isHighlight = false,
            isPublicDomain = true,
            additionalImages = listOf(
                "https://images.metmuseum.org/CRDImages/aa/original/DT159.jpg",
                "https://images.metmuseum.org/CRDImages/aa/original/DT159.jpg",
                "https://images.metmuseum.org/CRDImages/aa/original/DT159.jpg",
            ),
            artistAlphaSort = "German",
            artistEndDate = "active 1480–1530",
            artistGender = "Female",
            artistPrefix = "German",
            artistRole = "Artist",
            artistSuffix = "German",
            artistULAN_URL = "https://www.metmuseum.org/art/collection/search/23953",
            artistWikidata_URL = "https://www.metmuseum.org/art/collection/search/23953",
            city = "Augsburg",
            classification = "Arms and Armor",
            country = "Germany",
            county = "Germany",
            culture = "German",
            dynasty = "German",
            constituents = listOf(
                ApiConstituent(
                    constituentID = 1,
                    name = "German",
                    constituentULAN_URL = "https://www.metmuseum.org/art/collection/search/23953",
                    constituentWikidata_URL = "url",
                    gender = "Female",
                    role = "Artist",
                )
            ),
            excavation = "German",
            geographyType = "German",
            isTimelineWork = false,
            linkResource = "German",
            metadataDate = "German",
            objectBeginDate = 1,
            objectEndDate = 1,
            objectName = "German",
            objectWikidata_URL = "German",
            portfolio = "German",
            primaryImageSmall = "German",
            region = "German",
            reign = "German",
            repository = "German",
            rightsAndReproduction = "German",
            river = "German",
            GalleryNumber = "German",
            subregion = "German",
            tags = listOf(
                ApiTag(
                    term = "German",
                    AAT_URL = "German",
                    Wikidata_URL = "German"
                )
            ),
            locale = "German",
            period = "German",
            locus = "German",
            measurements = listOf(
                ApiMeasurement(
                    elementDescription = "German",
                    elementMeasurements = ApiElementMeasurements(
                        Depth = null,
                        Diameter = null,
                        Height = null,
                        Length = null,
                        Weight = null,
                        Width = null,
                        CB = null,
                        Circumference = null,
                        Rim = null,
                        Thickness = null,
                    ),
                    elementName = "German"
                )
            ),
            state = "German"
        )
    )
}