package com.example.domain.models

data class PaginationResponse<T>(
    val data: List<T>,
    val page: Int,
    val pageSize: Int,
)
