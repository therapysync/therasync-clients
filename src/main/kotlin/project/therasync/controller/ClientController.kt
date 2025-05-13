package project.therasync.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import project.therasync.data.dto.AssignClientRequest
import project.therasync.data.dto.ClientResponse
import project.therasync.service.ClientService

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Clients", description = "Управление клиентами и назначением психологов")
class ClientController(
    private val clientService: ClientService
) {

    @Operation(summary = "Назначить клиента психологу")
    @PostMapping("/assign")
    fun assign(@RequestBody request: AssignClientRequest): ResponseEntity<*> =
        ResponseEntity.ok(clientService.assignClient(request))

    @Operation(summary = "Получить клиентов по ID психолога")
    @GetMapping("/by-psychologist/{id}")
    fun getClients(@PathVariable id: String): ResponseEntity<List<ClientResponse>> =
        ResponseEntity.ok(clientService.getClients(id))

    @Operation(summary = "Архивировать клиента по linkId")
    @DeleteMapping("/archive/{linkId}")
    fun archiveClient(@PathVariable linkId: Long): ResponseEntity<*> =
        ResponseEntity.ok(clientService.archiveClient(linkId))

    @Operation(summary = "Архивировать клиента по ID клиента и психолога")
    @DeleteMapping("/archive")
    fun archiveClientByIds(
        @RequestParam psychologistId: String,
        @RequestParam clientId: String
    ): ResponseEntity<Void> {
        clientService.archiveClientByIds(psychologistId, clientId)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "Поиск клиентов по имени")
    @GetMapping("/search")
    fun searchClients(@RequestParam name: String): ResponseEntity<List<ClientResponse>> =
        ResponseEntity.ok(clientService.searchClientsByName(name))
}
