package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.PsychologistClient

interface PsychologistClientRepository : JpaRepository<PsychologistClient, Long> {
    fun findByPsychologistId(psychologistId: Long): List<PsychologistClient>
}
