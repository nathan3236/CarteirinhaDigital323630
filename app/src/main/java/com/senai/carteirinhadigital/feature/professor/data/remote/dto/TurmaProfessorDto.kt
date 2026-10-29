package com.senai.carteirinhadigital.feature.professor.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TurmaProfessorDto(
    val id: String,
    val codigo: String,
    val curso: String,
    val semestre: String,
    val quantidadeAlunos: Int,
    val diasSemana: List<String> = emptyList(),
    val horarioInicio: String = "",
    val horarioFim: String = ""
)
