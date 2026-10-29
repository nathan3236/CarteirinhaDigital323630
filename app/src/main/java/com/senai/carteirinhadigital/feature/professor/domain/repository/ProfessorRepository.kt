package com.senai.carteirinhadigital.feature.professor.domain.repository

import com.senai.carteirinhadigital.feature.professor.data.remote.dto.TurmaProfessorDto
import com.senai.carteirinhadigital.feature.professor.data.remote.dto.UnidadeProfessorDto

interface ProfessorRepository {
    suspend fun listarTurmas(): Result<List<TurmaProfessorDto>>
    suspend fun listarUnidadesCurriculares(): Result<List<UnidadeProfessorDto>>
}
