package project.therasync.service

import org.springframework.stereotype.Service
import project.therasync.data.dto.ClientResponse
import project.therasync.data.model.PsychologistClient
import project.therasync.data.repository.PsychologistClientRepository
import project.therasync.feign.GetUsersRequest
import project.therasync.feign.TherasyncAuthFeignClient

@Service
class ClientService(
    private val therasyncAuthFeignClient: TherasyncAuthFeignClient,
    private val linkRepository: PsychologistClientRepository,
) {
    fun assignClient(
        clientId: Long,
        psychologistId: Long,
    ): PsychologistClient {
        val psychologist = therasyncAuthFeignClient.getUser(psychologistId)
        val client = therasyncAuthFeignClient.getUser(clientId)
        return linkRepository.save(
            PsychologistClient(
                psychologistId = psychologist.id,
                clientId = client.id,
            ),
        )
    }

    fun getClients(psychologistId: Long): List<ClientResponse> {
        val clientLinks = linkRepository.findByPsychologistId(psychologistId)
        val clientIds = clientLinks.map { it.clientId }.toSet().toList()
        println(clientIds)

        return therasyncAuthFeignClient.getUsers(GetUsersRequest(clientIds)).map {
            ClientResponse(
                clientId = it.id,
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName,
            )
        }
    }

    fun archiveClient(linkId: Long) {
        linkRepository.deleteById(linkId)
    }

    fun archiveClientByIds(
        psychologistId: Long,
        clientId: Long,
    ) {
        val link =
            linkRepository
                .findAll()
                .firstOrNull { it.psychologistId == psychologistId && it.clientId == clientId }
                ?: throw IllegalArgumentException("Связь не найдена")
        linkRepository.delete(link)
    }
}
