package com.senai.carteirinhadigital.feature.professor.data.repository

import com.senai.carteirinhadigital.feature.professor.data.remote.dto.TurmaProfessorDto
import com.senai.carteirinhadigital.feature.professor.data.remote.dto.UnidadeProfessorDto
import com.senai.carteirinhadigital.feature.professor.data.remote.service.ProfessorApi
import com.senai.carteirinhadigital.feature.professor.domain.repository.ProfessorRepository
import retrofit2.HttpException
import java.io.IOException

class ApiProfessorRepositoryImpl(private val api: ProfessorApi) : ProfessorRepository {
    override suspend fun listarTurmas(): Result<List<TurmaProfessorDto>> = runCatching {
        api.listarTurmas()
    }.recoverCatching { erro -> throw traduzirErro(erro) }

    override suspend fun listarUnidadesCurriculares(): Result<List<UnidadeProfessorDto>> = runCatching {
        api.listarUnidadesCurriculares()
    }.recoverCatching { erro -> throw traduzirErro(erro) }

    private fun traduzirErro(erro: Throwable): Throwable = when (erro) {
        is HttpException -> when (erro.code()) {
            401, 403 -> IllegalStateException("Sessão inválida ou expirada. Entre novamente.")
            else -> IllegalStateException("Erro da API (${erro.code()}). Confira se o servidor está rodando.")
        }
        is IOException -> IllegalStateException("Não foi possível conectar à API. Confira se ela está rodando e se o endereço está correto.")
        else -> IllegalStateException(erro.message ?: "Não foi possível carregar os dados.")
    }
}
