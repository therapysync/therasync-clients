package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.model.PsychologistClient
import project.therasync.data.model.Role
import project.therasync.data.repository.PsychologistClientRepository
import project.therasync.data.repository.UserRepository
import project.therasync.data.dto.AssignClientRequest
import project.therasync.data.dto.ClientResponse

@Service
class ClientService(
    private val userRepository: UserRepository,
    private val linkRepository: PsychologistClientRepository
) {

    fun assignClient(request: AssignClientRequest): PsychologistClient {
        val psychologist = userRepository.findById(request.psychologistId)
            .orElseThrow { IllegalArgumentException("Психолог не найден") }

        val client = userRepository.findById(request.clientId)
            .orElseThrow { IllegalArgumentException("Клиент не найден") }

        return linkRepository.save(
            PsychologistClient(
                psychologistId = psychologist.id,
                clientId = client.id
            )
        )
    }

    fun getClients(psychologistId: String): List<ClientResponse> {
        val clientLinks = linkRepository.findByPsychologistId(psychologistId)
        val clientIds = clientLinks.map { it.clientId }.toSet()
        val clients = userRepository.findAllById(clientIds)

        return clients.map {
            ClientResponse(
                clientId = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email
            )
        }
    }

    fun archiveClient(linkId: Long): Boolean {
        val link = linkRepository.findById(linkId)
            .orElseThrow { IllegalArgumentException("Связь не найдена") }

        linkRepository.delete(link)
        return true
    }

    fun archiveClientByIds(psychologistId: String, clientId: String) {
        val link = linkRepository.findAll()
            .firstOrNull { it.psychologistId == psychologistId && it.clientId == clientId }
            ?: throw IllegalArgumentException("Связь не найдена")

        linkRepository.delete(link)
    }

    fun searchClientsByName(name: String): List<ClientResponse> {
        return userRepository.findByFirstNameContainingIgnoreCase(name)
            .filter { it.role == Role.CLIENT }
            .map {
                ClientResponse(
                    clientId = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    email = it.email
                )
            }
    }
}
