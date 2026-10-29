package com.senai.carteirinhadigital.feature.aluno.unidadescurriculares.domain.repository

import com.senai.carteirinhadigital.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}