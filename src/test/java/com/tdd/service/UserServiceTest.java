package com.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.tdd.model.User;
import com.tdd.repo.UserRepository;
import com.tdd.service.exception.UserServiceException;

@DisplayName("User related service test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private static UserServiceImpl userService;
	@Mock
	private static UserRepository userRepository;
	private static String fName = "Rahul";
	private static String lName = "Kumar";
	private static String email = "rahul.kumar0850@gmail.com";
	private static String password = "123456";
	private static String repeatPassword = "123456";

	@BeforeAll
	public static void setUp() {
		userService = new UserServiceImpl();
		userRepository = Mockito.mock(UserRepository.class);
		fName = "Rahul";
		lName = "Kumar";
		email = "rahul.kumar0850@gmail.com";
		password = "123456";
		repeatPassword = "123456";
	}

	@Order(1)
	@DisplayName("Test Create User with provided details")
	@Test
	public void testCreateUser_WhenUserDetailsProvided_ReturnUserObject() {
//		Arrange
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(User.build(fName, lName, email, 1L));
//		Act

		User user = userService.createUser(fName, lName, email, password, repeatPassword);

//		Assert

		assertNotNull(user, "The Create User method should not return null.");
		assertEquals(fName, user.getFName(), "User's First Name is incorrect.");
		assertEquals(lName, user.getLName(), "User's Last Name is incorrect.");
		assertEquals(email, user.getEmail(), "User's First Name is incorrect.");
		assertNotNull(user.getId(), "User Id can not be null.");
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
	}

	@Order(2)
	@DisplayName("Test Create User throws Illegal Arguement Exception when first name is empty")
	@Test
	public void testCreateUser_WhenFirstNameIsEmpty_ThrowsIllegalArguementException() {

//		Arrange
		fName = "";
//		Act & Assert
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			User user = userService.createUser(fName, lName, email, password, repeatPassword);
		}, "Empty First name should have caused an Illegal Arguement Expception");

	}

	@Order(3)
	@DisplayName("When User Not Created Throw UserException")
	@Test
	public void testCreateUser_whenSaveMethodThrowsException_ThenThrowUserException() {

//		Arrange
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(RuntimeException.class);
		fName="Rahul";

//		Act

		assertThrows(UserServiceException.class, () -> {
			userService.createUser(fName, lName, email, password, repeatPassword);
		}, "Should have thrown UserException instead ");
//		Assert
	}

}
