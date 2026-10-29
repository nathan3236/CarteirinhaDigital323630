package com.senai.carteirinhadigital.feature.professor.data.remote.service

import com.senai.carteirinhadigital.feature.professor.data.remote.dto.TurmaProfessorDto
import com.senai.carteirinhadigital.feature.professor.data.remote.dto.UnidadeProfessorDto
import retrofit2.http.GET

interface ProfessorApi {
    @GET("professores/me/turmas")
    suspend fun listarTurmas(): List<TurmaProfessorDto>

    @GET("professores/me/unidades-curriculares")
    suspend fun listarUnidadesCurriculares(): List<UnidadeProfessorDto>
}
