package com.sample.ecommerce.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.entities.UserRole;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.helpers.GlobalExceptionHandler;
import com.sample.ecommerce.pojos.Response;
import com.sample.ecommerce.services.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;
    private static GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
    private static LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();

    private static String CREATE_EMPLOYEE_URL = "/admin/create-employee";

    private static String testName = "test-name";
    private static String testEmail = "test@mail.com";
    private static String testPassword = "test-password";
    private static String testHashedPassword = "test-hashed-password";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setControllerAdvice(globalExceptionHandler)
                .setValidator(validator)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Successful Employee Creation Test")
    void testCreateEmployeeSuccess() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        User mockUser = new User(testName, testEmail, testHashedPassword, UserRole.ADMIN);
        when(adminService.createEmployee(requestDto)).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.name").value(testName))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.SUCCESS.toString()));

        verify(adminService, times(1)).createEmployee(requestDto);
    }

    @Test
    @DisplayName("Test Employee Creation With Duplicate Email")
    void testCreateEmployeeWithDuplicateEmail() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        when(adminService.createEmployee(requestDto)).thenThrow(new UserException("Employee Already Exists With Given Email"));

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Employee Already Exists With Given Email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()));
        ;

        verify(adminService, times(1)).createEmployee(requestDto);
    }

    @Test
    @DisplayName("Test Employee Creation With Empty Role")
    void testCreateEmployeeWithEmptyRole() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, testPassword, null);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Role is Required"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()));
        ;

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Empty Name")
    void testCreateEmployeeWithEmptyName() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(null, testEmail, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Name is Required"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Blank Name")
    void testCreateEmployeeWithBlankName() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto("   ", testEmail, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Name must be between 5 and 20 characters"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Short Name")
    void testCreateEmployeeWithShortName() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto("name", testEmail, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Name must be between 5 and 20 characters"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Long Name")
    void testCreateEmployeeWithLongName() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto("abcdefghijklmnopqrstuvwxyz", testEmail, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Name must be between 5 and 20 characters"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Empty Email")
    void testCreateEmployeeWithEmptyEmail() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, null, testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/create-employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("User Email is Required"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Blank Email")
    void testCreateEmployeeWithBlankEmail() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, "  ", testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid Email"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Invalid Email")
    void testCreateEmployeeWithInvalidEmail() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, "test-example.com", testPassword, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid Email"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Empty Password")
    void testCreateEmployeeWithEmptyPassword() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, null, UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Password is Required"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Blank Password")
    void testCreateEmployeeWithBlankPassword() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, "  ", UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Password is Required"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Short Password")
    void testCreateEmployeeWithShortPassword() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, "pass", UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Password must be between 8 and 20 characters"));

        verify(adminService, never()).createEmployee(any());
    }

    @Test
    @DisplayName("Test Employee Creation With Long Password")
    void testCreateEmployeeWithLongPassword() throws Exception {
        EmployeeRegisterDto requestDto = new EmployeeRegisterDto(testName, testEmail, "123456789101112131415", UserRole.ADMIN);
        String requestJson = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_EMPLOYEE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(Response.Status.FAILURE.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Password must be between 8 and 20 characters"));

        verify(adminService, never()).createEmployee(any());
    }
}
