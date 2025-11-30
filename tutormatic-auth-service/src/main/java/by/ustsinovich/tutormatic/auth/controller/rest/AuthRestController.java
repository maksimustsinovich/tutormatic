package by.ustsinovich.tutormatic.auth.controller.rest;

import by.ustsinovich.tutormatic.auth.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication API",
        description = "Security operations."
)
@RequestMapping("/api/auth")
public interface AuthRestController {

    @Operation(
            summary = "New user registration.",
            description =
                    "Creates a new user in the system. " +
                    "After successful registration, the user can log in to the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Register successful."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "User with this username already exists.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    void register(
            @Valid
            @RequestBody
            RegisterRequest request
    );

    @Operation(
            summary = "Login into system.",
            description = "User authentication."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful.",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid credentials.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    AuthResponse login(
            @Valid
            @RequestBody
            LoginRequest request
    );

    @Operation(
            summary = "Token refresh.",
            description = "Getting new pair of access and refresh tokens."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Token refresh successful.",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid refresh token.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/refresh")
    AuthResponse refresh(
            @Valid
            @RequestBody
            RefreshRequest request
    );

    @Operation(
            summary = "Logout from system.",
            description = "User logout and token invalidation."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Logout successful."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/logout")
    void logout(
            @Valid
            @RequestBody
            String accessToken
    );

}
