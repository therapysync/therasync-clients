package project.therasync.data.repository

import org.springframework.data.jpa.repository.JpaRepository
import project.therasync.data.model.Appointment

interface AppointmentRepository : JpaRepository<Appointment, Long> {
    fun findByPsychologistId(id: Long): List<Appointment>
    fun findByClientId(id: Long): List<Appointment>
}
