package com.soat.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getClient(@RequestParam(name = "cpf") final String cpf) {
        return ResponseEntity.ok(searchClients(cpf));
    }

    private List<Map<String, String>> buildClients() {
        final var clients = new ArrayList<Map<String, String>>();

        for (int i = 0; i <= 5; i++) {
            final var client = new ConcurrentHashMap<String, String>();
            client.put("cpf", String.format("%s%s", "1231231231", i));
            clients.add(client);
        }

        return clients;
    }

    private List<Map<String, String>> searchClients(final String cpf) {
        return buildClients().stream()
                .filter(client -> client.containsValue(cpf))
                .collect(Collectors.toList());
    }
}
