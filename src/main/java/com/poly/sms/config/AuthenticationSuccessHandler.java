// package com.poly.sms.config;

// import java.io.IOException;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//             Authentication authentication) throws ServletException, IOException {

//         response.sendRedirect("/sms/home");
//     }
// }
