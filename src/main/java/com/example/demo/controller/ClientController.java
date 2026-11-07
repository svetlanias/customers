package com.example.demo.controller;

import com.example.demo.dto.ActiveClientsResponse;
import com.example.demo.dto.ClientCreateRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.ClientUpdateRequest;
import com.example.demo.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponse create(@Valid @RequestBody ClientCreateRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<ClientResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable Long id,
                                 @Valid @RequestBody ClientUpdateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/active")
    public ActiveClientsResponse getAllActive() {

        return service.getAllActive();
    }
}