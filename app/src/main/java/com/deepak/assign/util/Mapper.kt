package com.deepak.assign.util

import com.deepak.assign.entity.PositionModel
import com.deepak.assign.entity.SearchModel

fun SearchModel.toPositionModel() = PositionModel(
    company = organization_name,
    companyLogo = null,
    companyUrl = url,
    createdAt = start_date,
    description = null,
    howToApply = null,
    id = id,
    location = locations.toString(),
    title = position_title,
    type = null,
    url = url
)