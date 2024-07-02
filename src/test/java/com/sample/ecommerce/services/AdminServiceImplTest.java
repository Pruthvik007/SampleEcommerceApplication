package com.sample.ecommerce.services;

import com.sample.ecommerce.dtos.EmployeeRegisterDto;
import com.sample.ecommerce.entities.User;
import com.sample.ecommerce.entities.UserRole;
import com.sample.ecommerce.entities.UserStatus;
import com.sample.ecommerce.exceptions.UserException;
import com.sample.ecommerce.repositories.UserRepository;
import com.sample.ecommerce.services.impl.AdminServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    public static final String testName = "Test Name";
    public static final String testMail = "test@mail.com";
    public static final String testPassword = "password";
    public static final String testHashedPassword = "hashed-password";
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    @DisplayName("Successfully Create a New Employee when Valid Data is Provided")
    public void testCreateEmployeeWithValidDetails() throws UserException {
        EmployeeRegisterDto employeeRegisterDto = new EmployeeRegisterDto(testName, testMail, testPassword, UserRole.EMPLOYEE);
        when(userRepository.findByEmail(employeeRegisterDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(employeeRegisterDto.getPassword())).thenReturn(testHashedPassword);
        User savedUser = new User(testName, testMail, testHashedPassword, UserRole.EMPLOYEE);
        savedUser.setStatus(UserStatus.CREATED);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = adminService.createEmployee(employeeRegisterDto);

        assertNotNull(result);
        assertEquals(testName, result.getName());
        assertEquals(testMail, result.getEmail());
        assertEquals(testHashedPassword, result.getPassword());
        assertEquals(UserRole.EMPLOYEE, result.getUserRole());
        assertEquals(UserStatus.CREATED, result.getStatus());
    }

    @Test
    @DisplayName("Test to Create Employee With Existing Email")
    public void testCreateEmployeeWithExistingEmail() {
        EmployeeRegisterDto employeeRegisterDto = new EmployeeRegisterDto(testName, testMail, testPassword, UserRole.EMPLOYEE);
        when(userRepository.findByEmail(employeeRegisterDto.getEmail())).thenReturn(Optional.of(new User()));
        UserException exception = assertThrows(UserException.class, () -> {
            adminService.createEmployee(employeeRegisterDto);
        });
        assertEquals("Employee Already Exists With Given Email", exception.getMessage());
    }

    @Test
    @DisplayName("Test to Create Employee With Invalid Role")
    public void testCreateEmployeeWithInvalidRole() {
        EmployeeRegisterDto employeeRegisterDto = new EmployeeRegisterDto(testName, testMail, testPassword, UserRole.CUSTOMER);
        UserException exception = assertThrows(UserException.class, () -> {
            adminService.createEmployee(employeeRegisterDto);
        });
        assertEquals("Invalid User Role", exception.getMessage());
    }

    @Test
    @DisplayName("Test to Check if Password is correctly encoded Before saving the Employee")
    public void testPasswordEncoding() throws UserException {
        EmployeeRegisterDto employeeRegisterDto = new EmployeeRegisterDto(testName, testMail, testPassword, UserRole.ADMIN);
        User savedUser = new User(testName, testMail, testHashedPassword, UserRole.ADMIN);
        savedUser.setStatus(UserStatus.CREATED);

        when(userRepository.findByEmail(employeeRegisterDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(employeeRegisterDto.getPassword())).thenReturn(testHashedPassword);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        User result = adminService.createEmployee(employeeRegisterDto);

        assertEquals(testHashedPassword, result.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Test Employee Status is set to CREATED upon Successful Creation")
    public void testEmployeeStatusCreatedOnCreation() {
        EmployeeRegisterDto employeeRegisterDto = new EmployeeRegisterDto(testName, testMail, testPassword, UserRole.ADMIN);
        User employee = new User(testName, testMail, testPassword, UserRole.ADMIN);
        employee.setStatus(UserStatus.CREATED);

        when(userRepository.findByEmail(employeeRegisterDto.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(employeeRegisterDto.getPassword())).thenReturn(testHashedPassword);
        when(userRepository.save(any(User.class))).thenReturn(employee);
        try {
            User createdEmployee = adminService.createEmployee(employeeRegisterDto);
            assertEquals(UserStatus.CREATED, createdEmployee.getStatus());
        } catch (UserException e) {
            fail("Exception thrown when it shouldn't have: " + e.getMessage());
        }
    }
}
