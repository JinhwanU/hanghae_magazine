//package com.sparta.hanghae_magazine.service;
//
//import com.sparta.hanghae_magazine.advice.RestException;
//import com.sparta.hanghae_magazine.controller.UserRestController;
//import com.sparta.hanghae_magazine.domain.RefreshToken;
//import com.sparta.hanghae_magazine.domain.Users;
//import com.sparta.hanghae_magazine.dto.LoginRequestDto;
//import com.sparta.hanghae_magazine.dto.RegisterRequestDto;
//import com.sparta.hanghae_magazine.dto.TokenResponseDto;
//import com.sparta.hanghae_magazine.repository.UserRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.client.HttpClientErrorException;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.transaction.Transactional;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.Principal;
//import java.util.*;
//
//import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
////@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    UserService userService;
//
//
//    @Test
//    @DisplayName("회원가입 - 성공")
//    void registerUser() {
//        //given
//        RegisterRequestDto requestDto = new RegisterRequestDto("test","1234","test");
//        userRepository.save(Users.builder()
//                .username(requestDto.getUsername())
//                .password(requestDto.getPassword())
//                .realName(requestDto.getRealName())
//                .roles(Collections.singletonList("ROLE_USER"))
//                .build());
//
//        assertThat(userRepository.findByUsername(requestDto.getUsername())).isNotEmpty();
//    }
//
//    @Test
//    @DisplayName("회원가입실패 - 중복")
//    void registerUserFail() {
//        //given
//        RegisterRequestDto member1 = new RegisterRequestDto("test", "1234", "test");
//        RegisterRequestDto member2 = new RegisterRequestDto("test", "1234", "test");
//
//        //when
//        userRepository.save(Users.builder()
//                .username(member1.getUsername())
//                .password(member1.getPassword())
//                .realName(member1.getRealName())
//                .roles(Collections.singletonList("ROLE_USER"))
//                .build());
//        RestException e = assertThrows(RestException.class, () -> userService.registerUser(member2));
//
//        //then
//        assertThat(e.getMessage()).isEqualTo("이미 존재하는 username입니다.");
//    }
//
//    @Test
//    @DisplayName("로그인 성공")
//    void login() {
//        //given
//        LoginRequestDto requestDto = new LoginRequestDto("test", "1234");
//
//        //when
//
//
//        //then
//    }
//
//    @Test
//    void logout() {
//        HttpServletRequest request = new HttpServletRequest() {
//            @Override
//            public Object getAttribute(String name) {
//                return null;
//            }
//
//            @Override
//            public Enumeration<String> getAttributeNames() {
//                return null;
//            }
//
//            @Override
//            public String getCharacterEncoding() {
//                return null;
//            }
//
//            @Override
//            public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
//
//            }
//
//            @Override
//            public int getContentLength() {
//                return 0;
//            }
//
//            @Override
//            public long getContentLengthLong() {
//                return 0;
//            }
//
//            @Override
//            public String getContentType() {
//                return null;
//            }
//
//            @Override
//            public ServletInputStream getInputStream() throws IOException {
//                return null;
//            }
//
//            @Override
//            public String getParameter(String name) {
//                return null;
//            }
//
//            @Override
//            public Enumeration<String> getParameterNames() {
//                return null;
//            }
//
//            @Override
//            public String[] getParameterValues(String name) {
//                return new String[0];
//            }
//
//            @Override
//            public Map<String, String[]> getParameterMap() {
//                return null;
//            }
//
//            @Override
//            public String getProtocol() {
//                return null;
//            }
//
//            @Override
//            public String getScheme() {
//                return null;
//            }
//
//            @Override
//            public String getServerName() {
//                return null;
//            }
//
//            @Override
//            public int getServerPort() {
//                return 0;
//            }
//
//            @Override
//            public BufferedReader getReader() throws IOException {
//                return null;
//            }
//
//            @Override
//            public String getRemoteAddr() {
//                return null;
//            }
//
//            @Override
//            public String getRemoteHost() {
//                return null;
//            }
//
//            @Override
//            public void setAttribute(String name, Object o) {
//
//            }
//
//            @Override
//            public void removeAttribute(String name) {
//
//            }
//
//            @Override
//            public Locale getLocale() {
//                return null;
//            }
//
//            @Override
//            public Enumeration<Locale> getLocales() {
//                return null;
//            }
//
//            @Override
//            public boolean isSecure() {
//                return false;
//            }
//
//            @Override
//            public RequestDispatcher getRequestDispatcher(String path) {
//                return null;
//            }
//
//            @Override
//            public String getRealPath(String path) {
//                return null;
//            }
//
//            @Override
//            public int getRemotePort() {
//                return 0;
//            }
//
//            @Override
//            public String getLocalName() {
//                return null;
//            }
//
//            @Override
//            public String getLocalAddr() {
//                return null;
//            }
//
//            @Override
//            public int getLocalPort() {
//                return 0;
//            }
//
//            @Override
//            public ServletContext getServletContext() {
//                return null;
//            }
//
//            @Override
//            public AsyncContext startAsync() throws IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
//                return null;
//            }
//
//            @Override
//            public boolean isAsyncStarted() {
//                return false;
//            }
//
//            @Override
//            public boolean isAsyncSupported() {
//                return false;
//            }
//
//            @Override
//            public AsyncContext getAsyncContext() {
//                return null;
//            }
//
//            @Override
//            public DispatcherType getDispatcherType() {
//                return null;
//            }
//
//            @Override
//            public String getAuthType() {
//                return null;
//            }
//
//            @Override
//            public Cookie[] getCookies() {
//                return new Cookie[0];
//            }
//
//            @Override
//            public long getDateHeader(String name) {
//                return 0;
//            }
//
//            @Override
//            public String getHeader(String name) {
//                return "test";
//            }
//
//            @Override
//            public Enumeration<String> getHeaders(String name) {
//                return null;
//            }
//
//            @Override
//            public Enumeration<String> getHeaderNames() {
//                return null;
//            }
//
//            @Override
//            public int getIntHeader(String name) {
//                return 0;
//            }
//
//            @Override
//            public String getMethod() {
//                return null;
//            }
//
//            @Override
//            public String getPathInfo() {
//                return null;
//            }
//
//            @Override
//            public String getPathTranslated() {
//                return null;
//            }
//
//            @Override
//            public String getContextPath() {
//                return null;
//            }
//
//            @Override
//            public String getQueryString() {
//                return null;
//            }
//
//            @Override
//            public String getRemoteUser() {
//                return null;
//            }
//
//            @Override
//            public boolean isUserInRole(String role) {
//                return false;
//            }
//
//            @Override
//            public Principal getUserPrincipal() {
//                return null;
//            }
//
//            @Override
//            public String getRequestedSessionId() {
//                return null;
//            }
//
//            @Override
//            public String getRequestURI() {
//                return null;
//            }
//
//            @Override
//            public StringBuffer getRequestURL() {
//                return null;
//            }
//
//            @Override
//            public String getServletPath() {
//                return null;
//            }
//
//            @Override
//            public HttpSession getSession(boolean create) {
//                return null;
//            }
//
//            @Override
//            public HttpSession getSession() {
//                return null;
//            }
//
//            @Override
//            public String changeSessionId() {
//                return null;
//            }
//
//            @Override
//            public boolean isRequestedSessionIdValid() {
//                return false;
//            }
//
//            @Override
//            public boolean isRequestedSessionIdFromCookie() {
//                return false;
//            }
//
//            @Override
//            public boolean isRequestedSessionIdFromURL() {
//                return false;
//            }
//
//            @Override
//            public boolean isRequestedSessionIdFromUrl() {
//                return false;
//            }
//
//            @Override
//            public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
//                return false;
//            }
//
//            @Override
//            public void login(String username, String password) throws ServletException {
//
//            }
//
//            @Override
//            public void logout() throws ServletException {
//
//            }
//
//            @Override
//            public Collection<Part> getParts() throws IOException, ServletException {
//                return null;
//            }
//
//            @Override
//            public Part getPart(String name) throws IOException, ServletException {
//                return null;
//            }
//
//            @Override
//            public <T extends HttpUpgradeHandler> T upgrade(Class<T> httpUpgradeHandlerClass) throws IOException, ServletException {
//                return null;
//            }
//        };
//        userService.logout(request);
//    }
//
//
////    @Transactional
////    public TokenResponseDto login(LoginRequestDto requestDto) {
////        Users user = userRepository.findByUsername(requestDto.getUsername())
////                .orElseThrow(() -> new RestException(HttpStatus.BAD_REQUEST, "가입되지 않은 username 입니다."));
////        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
////            throw new RestException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");
////        }
////        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRoles());
////        String refreshToken = jwtTokenProvider.createRefreshToken(user.getUsername(), user.getRoles());
////        tokenRepository.save(new RefreshToken(refreshToken));
////
////        return TokenResponseDto.builder()
////                .ACCESS_TOKEN(accessToken)
////                .REFRESH_TOKEN(refreshToken)
////                .build();
////    }
////
////    @Transactional
////    public void logout(HttpServletRequest request) {
////        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
////        tokenRepository.deleteByRefreshToken(refreshToken);
////        SecurityContextHolder.clearContext();
////    }
//}
