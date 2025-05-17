package project.therasync.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import project.therasync.data.dto.ClientResponse
import project.therasync.data.model.PsychologistClient
import project.therasync.service.ClientService

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Управление клиентами и назначением психологов")
class ClientController(
    private val clientService: ClientService,
) {
    @Operation(summary = "Назначить клиента психологу")
    @PostMapping("/assign/{clientId}")
    fun assign(
        @RequestHeader("X-Client-Id") psychologistId: Long,
        @PathVariable clientId: Long,
    ): PsychologistClient = clientService.assignClient(clientId, psychologistId).also { println("123") }

    @Operation(summary = "Получить клиентов по ID психолога")
    @GetMapping("/by-psychologist")
    fun getClients(
        @RequestHeader("X-Client-Id") psychologistId: Long,
    ): List<ClientResponse> = clientService.getClients(psychologistId)

    @Operation(summary = "Архивировать клиента по linkId")
    @DeleteMapping("/archive/{linkId}")
    fun archiveClient(
        @PathVariable linkId: Long,
    ) = clientService.archiveClient(linkId)

    @Operation(summary = "Архивировать клиента по ID клиента и психолога")
    @DeleteMapping("/archive")
    fun archiveClientByIds(
        @RequestHeader("X-Client-Id") psychologistId: Long,
        @RequestParam clientId: Long,
    ) = clientService.archiveClientByIds(psychologistId, clientId)
}
