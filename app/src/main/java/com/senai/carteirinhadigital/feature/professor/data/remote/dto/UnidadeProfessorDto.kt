package com.senai.carteirinhadigital.feature.professor.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UnidadeProfessorDto(
    val id: String,
    val nome: String,
    val turmaId: String,
    val turma: String,
    val cargaHoraria: Int,
    val quantidadeAulas: Int,
    val aulasRealizadas: Int,
    val diasSemana: List<String> = emptyList(),
    val horarioInicio: String = "",
    val horarioFim: String = ""
)
