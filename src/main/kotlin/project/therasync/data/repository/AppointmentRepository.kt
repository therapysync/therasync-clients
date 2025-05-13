package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.Appointment

interface AppointmentRepository : JpaRepository<Appointment, Long> {
    fun findByPsychologistId(id: String): List<Appointment>
    fun findByClientId(id: String): List<Appointment>
}
