package com.fpineda.challenge.usersapi.adapter.web;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpineda.challenge.usersapi.core.command.CreateUserCommand;
import com.fpineda.challenge.usersapi.core.model.User;
import com.fpineda.challenge.usersapi.core.usecase.CreateUserUseCase;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller.UserController;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.controller.UserControllerNonRest;
import com.fpineda.challenge.usersapi.infrastructure.adapter.web.dto.UpdateUserDto;
import com.fpineda.challenge.usersapi.utils.TestUtilsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = UserControllerNonRest.class)
class UserControllerNonRestTest {

    @Configuration
    @ComponentScan(basePackages = "com.fpineda.challenge.usersapi.infrastructure.adapter.web")
    static class TestConfiguration {

    }

    @Autowired
    MockMvc mockMvc;

    private static ObjectMapper mapper;

    @MockBean
    private CreateUserUseCase createUserUseCase;        

    @MockBean
    private UserController restController;

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void shouldCreateUser_Successfully() throws Exception {
        // Prepare data and mocks
        var createUserDto = TestUtilsFactory.createUserDto();
        var userExpected = TestUtilsFactory.createUser();

        when(createUserUseCase.create(any(CreateUserCommand.class))).thenReturn(userExpected);

        // Execute and Assertions
        mockMvc.perform(post("/createUsers").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createUserDto))).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", comparesEqualTo(userExpected.getName())));
    }

    @Test
    void shouldReturn_List_With_OneUser() throws Exception {
        // Prepare data
        var userList = TestUtilsFactory.createListUsers();

        ResponseEntity<List<User>> result = ResponseEntity.ok(userList);

        when(restController.fetchAllUsers()).thenReturn(result);

        // Execution and Assertions
        mockMvc.perform(get("/getusers")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    void shouldReturn_UserById_Sucessfully() throws Exception {
        // Prepare data
        var user = TestUtilsFactory.createUser();

        ResponseEntity<User> result = ResponseEntity.ok(user);

        when(restController.fetchUserById(1L)).thenReturn(result);

        // Execution and assertions
        mockMvc.perform(get("/getusersById" + "/" + 1L)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", comparesEqualTo(user.getName())));
    }

    @Test
    void shouldReturn_StatusNotFound() throws Exception {
        // Prepare Data
        when(restController.fetchUserById(anyLong())).thenThrow(new EntityNotFoundException());

        // Execution and Assertion
        mockMvc.perform(get("/getusersById" + "/" + 1L)).andExpect(status().isNotFound());
    }

    @Test
    void shouldUpdateUser_Successfully() throws Exception {
        // Prepare data and mocks
        var newName = "Fernando updated";
        var updateUserDto = TestUtilsFactory.createUpdateUserDto();
        updateUserDto.setName(newName);
        
        var userExpected = TestUtilsFactory.createUser();
        userExpected.setName(newName);

        ResponseEntity<User> response = ResponseEntity.ok(userExpected);

        when(restController.updateUser(anyLong(), any(UpdateUserDto.class))).thenReturn(response);

        // Execute and Assertions
        mockMvc.perform(put("/updateUsersById/" + 1L).contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateUserDto))).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", comparesEqualTo(userExpected.getName())));
    }

    @Test
    void shouldDelete_UserById_Successfully() throws Exception {
        long id = 1L;
        // Assertions
        mockMvc.perform(delete("/deleteUsersById" + "/" + id)).andExpect(status().isOk());

    }


    static String asJsonString(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }


}
