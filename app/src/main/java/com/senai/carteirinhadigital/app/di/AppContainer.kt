package com.senai.carteirinhadigital.app.di

import com.senai.carteirinhadigital.core.auth.SessionTokenStore
import com.senai.carteirinhadigital.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.senai.carteirinhadigital.feature.login.domain.repository.LoginRepository
import com.senai.carteirinhadigital.feature.professor.domain.repository.ProfessorRepository

interface AppContainer {
    val sessionTokenStore: SessionTokenStore
    val loginRepository: LoginRepository
    val unidadeCurricularRepository: UnidadeCurricularRepository
    val professorRepository: ProfessorRepository
}
