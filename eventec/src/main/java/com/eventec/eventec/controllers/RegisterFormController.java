package com.eventec.eventec.controllers;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import com.eventec.eventec.config.ValidationCodeGenerator;
import com.eventec.eventec.models.UserItem;
import com.eventec.eventec.repositories.UserItemRepository;
import com.eventec.eventec.services.EmailService;
import com.eventec.eventec.services.UserItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import com.eventec.eventec.models.UserItem.EmailValidationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/api/users")

public class RegisterFormController {

    String confirmationSuccessUrl = "classpath:/templates/confirmationSuccess.html";
    private static final Logger logger = LoggerFactory.getLogger(RegisterFormController.class);
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error";
    private static final String USER_NOT_FOUND_MESSAGE = "User not found";
    private static final String CROSS_ORIGIN_URL = "http://localhost:3000";

    @Autowired
    private UserItemService userItemService;
    private final UserItemRepository userItemRepository;

    @GetMapping("/register")
    public String showCreatedForm(UserItem userItem) {
        return "login";
    }

    @Autowired
    public RegisterFormController(UserItemRepository userItemRepository) {
        this.userItemRepository = userItemRepository;
    }

    @Autowired
    private EmailService emailService;

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping("/myAccount")
    public ResponseEntity<?> getAccountDetails(@RequestParam String email, @RequestParam String password) {
        try {
            Optional<UserItem> userItemOpt = userItemRepository.findUserByEmailAndPassword(email, password);
            if (userItemOpt.isPresent()) {
                return new ResponseEntity<>(userItemOpt.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @DeleteMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(@RequestParam String email, @RequestParam String password) {
        try {
            Optional<UserItem> userItemOpt = userItemRepository.findUserByEmailAndPassword(email, password);
            if (userItemOpt.isPresent()) {
                UserItem userItem = userItemOpt.get();
                userItemRepository.delete(userItem);
                return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(USER_NOT_FOUND_MESSAGE, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String currentPassword, @RequestParam String newPassword) {
        try {
            Optional<UserItem> userItemOpt = userItemRepository.findUserByEmailAndPassword(email, currentPassword);
            if (userItemOpt.isPresent()) {
                UserItem userItem = userItemOpt.get();
                userItem.setPassword(newPassword);
                userItemRepository.save(userItem);
                return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid current password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @PostMapping("/create")
    public @ResponseBody ResponseEntity<UserItem> createUser(@RequestBody UserItem userItem) {
        if (userItem.getUserType() == UserItem.UserType.aluno ||
                userItem.getUserType() == UserItem.UserType.professor ||
                userItem.getUserType() == UserItem.UserType.diretor) {

            userItem.setEmailValidationType(UserItem.EmailValidationType.INSTITUTIONAL);
            userItem.setEmail(userItem.getEmailInstitucional()); // Set the email address
        } else {
            userItem.setEmailValidationType(UserItem.EmailValidationType.COMMON);
        }

        userItem.setEmailConfirmed(false); // Define initially as not confirmed

        // Generate the validation code and store it in the database
        String validationCode = ValidationCodeGenerator.generateValidationCode(16);
        userItem.setValidationCode(validationCode);

        UserItem createdUser = userItemService.createUserItem(userItem);

        // Send the email confirmation
        emailService.sendValidationEmail(userItem.getEmail(), validationCode);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = CROSS_ORIGIN_URL)
    @GetMapping("/confirmEmail")
    public RedirectView confirmEmail(@RequestParam("email") String email, @RequestParam("code") String code) {
        Optional<UserItem> userItemOpt = userItemRepository.findUserByEmail(email);

        if (userItemOpt.isPresent()) {
            UserItem userItem = userItemOpt.get();

            logger.info("Entered the confirmEmail method. Email: {}, Code: {}", email, code);
            if (!userItem.isEmailConfirmed() && userItem.getValidationCode().equals(code)) {
                userItem.setEmailConfirmed(true);
                logger.info("Email confirmed successfully for: {}", email);
                userItemService.save(userItem); // Update the confirmation state in the database

                String confirmationSuccessUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("resources/templates/confirmationSuccess.html")
                        .toUriString();

                RedirectView redirectView = new RedirectView(confirmationSuccessUrl);
                return redirectView;
            } else {
                // You can redirect to an error page if the code is invalid.
                String confirmationErrorUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/confirmationError.html")
                        .toUriString();

                RedirectView redirectView = new RedirectView(confirmationErrorUrl);
                return redirectView;
            }
        } else {
            // Redirect to a "User Not Found" page.
            String userNotFoundUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/userNotFound.html")
                    .toUriString();

            RedirectView redirectView = new RedirectView(userNotFoundUrl);
            return redirectView;
        }
    }
}