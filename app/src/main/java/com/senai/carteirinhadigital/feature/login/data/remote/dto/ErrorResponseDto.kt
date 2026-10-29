package com.senai.carteirinhadigital.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val message: String? = null
)
