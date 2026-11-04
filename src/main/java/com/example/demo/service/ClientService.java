package com.example.demo.service;

import com.example.demo.dto.ClientCreateRequest;
import com.example.demo.dto.ClientResponse;
import com.example.demo.dto.ClientUpdateRequest;
import com.example.demo.entity.ClientAccount;
import com.example.demo.entity.Status;
import com.example.demo.exception.ClientNotFoundException;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    @Transactional
    public ClientResponse create(ClientCreateRequest request) {
        ClientAccount client = ClientAccount.builder()
                .fullName(request.getFullName())
                .gender(request.getGender())
                .status(Status.ACTIVE)
                .build();
        return toResponse(repository.save(client));
    }

    @Transactional(readOnly = true)
    public List<ClientResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ClientResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    @Transactional
    public ClientResponse update(Long id, ClientUpdateRequest request) {
        ClientAccount client = getOrThrow(id);
        if (request.getFullName() != null) client.setFullName(request.getFullName());
        if (request.getGender() != null) client.setGender(request.getGender());
        if (request.getStatus() != null) client.setStatus(request.getStatus());
        // saveAndFlush, чтобы @PreUpdate отработал и modifyDttm обновился до формирования ответа
        return toResponse(repository.saveAndFlush(client));
    }

    @Transactional
    public void delete(Long id) {
        ClientAccount client = getOrThrow(id);
        repository.delete(client);
    }

    private ClientAccount getOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    private ClientResponse toResponse(ClientAccount c) {
        return ClientResponse.builder()
                .id(c.getId())
                .fullName(c.getFullName())
                .gender(c.getGender())
                .status(c.getStatus())
                .createDttm(c.getCreateDttm())
                .modifyDttm(c.getModifyDttm())
                .build();
    }
}