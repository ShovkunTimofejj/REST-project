package com.javarush.rest.service;

import com.javarush.rest.model.Client;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();


    @Override
    public void create(Client client) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(clientId);
        CLIENT_REPOSITORY_MAP.put(clientId,client);
        log.info("Create new client with id {}", clientId);
    }

    @Override
    public List<Client> readAll() {
        log.debug("Reading all clients");
        return new ArrayList<>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public Client read(int id) {
        log.debug("Reading client with id {}", id);
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (CLIENT_REPOSITORY_MAP.containsKey(id)) {
            client.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, client);
            log.info("Update client with id {}", id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        log.debug("Deleting client with id {}", id);
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }
}
