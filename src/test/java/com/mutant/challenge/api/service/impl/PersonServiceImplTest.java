package com.mutant.challenge.api.service.impl;

import com.mutant.challenge.domain.model.Person;
import com.mutant.challenge.utils.Jsons;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonServiceImplTest {


    @Autowired
    private MockMvc mockMvc;
    private static final Log log = LogFactory.getLog(PersonServiceImplTest.class);
    @Autowired
    private PersonServiceImpl service;

    @Test
    @DisplayName("Carga de persona a la BBDD")
    @Order(1)
    public void savePerson() throws Exception {
        Person p = new Person(1L, "User", "Test", 12345678, false);
        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(p)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();
        Assert.assertEquals((response.getResponse()).getContentAsString(), "Se ha dado de alta la persona correctamente");
    }

    @Test
    @DisplayName("Elimina persona cargada")
    @Order(2)
    public void deletePerson() throws Exception {
        Person p = new Person(1L, "User", "Test", 12345679, false);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(p)))
                .andDo(MockMvcResultHandlers.print());

        ResultActions response = this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/delete?dni=12345679")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Modifica de persona a la BBDD")
    @Order(3)
    public void updatePerson() throws Exception {

        Person p = new Person(1L, "User", "Test", 12345680, false);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(p)))
                .andDo(MockMvcResultHandlers.print());

        Person pm = new Person(1L, "User", "Test", 12345680, true);

        MvcResult response = this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/users/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Jsons.asJsonString(pm)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk()).andReturn();

        Assert.assertEquals((response.getResponse()).getContentAsString(), "Se modifico correctamente la persona con id 1");
    }

}
