package com.thejan.assessment.digikraft_assignment.data.model

import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("features") val features: List<Features>,
)