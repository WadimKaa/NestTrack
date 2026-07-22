package com.powakaz.nesttrack.feature_time.domain.model

import com.powakaz.nesttrack.feature_time.data.datasourse.remote.model.ConcessionDto

data class ConcessionList(
    val page: Int,
    val totalRecords: Int?,
    val hasMorePage: Boolean,
    val dataConcession: List<ConcessionDto>
)
