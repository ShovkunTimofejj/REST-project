package com.javarush.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.rest.controller.ClientController;
import com.javarush.rest.dto.ClientDTO;
import com.javarush.rest.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;



    @Test
    void createClientTest() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("Test");
        clientDTO.setEmail("Test@test.com");
        clientDTO.setPhone("1234567890");

        mockMvc.perform(post("/clients")
                .contentType("application/json")
                .content(asJsonString(clientDTO)))
                .andExpect(status().isCreated());

    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
