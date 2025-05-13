package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.PsychologistClient

interface PsychologistClientRepository : JpaRepository<PsychologistClient, Long> {
    fun findByPsychologistId(psychologistId: String): List<PsychologistClient>
    fun deleteByPsychologistIdAndClientId(psychologistId: String, clientId: String)
}
