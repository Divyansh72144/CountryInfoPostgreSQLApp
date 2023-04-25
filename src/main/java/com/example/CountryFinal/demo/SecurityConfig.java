//package com.example.CountryFinal.demo;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
//@Order(1)
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
// 
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .antMatchers("/login/**", "/error/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//            .oauth2Login()
//                .clientRegistrationRepository(clientRegistrationRepository())
//                .authorizedClientService(authorizedClientService());
//    }
// 
//    private ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(googleClientRegistration());
//    }
// 
//    private OAuth2AuthorizedClientService authorizedClientService() {
//        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
//    }
// 
//    private ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//            .clientId("638770866810-beh3mgkdgau6mm7scjdhrf7sveg0rhhg.apps.googleusercontent.com")
//            .clientSecret("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")
//            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
//            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//            .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
//            .scope("openid", "email", "profile")
//            .authorizationUri("https://accounts.google.com/o/oauth2/auth")
//            .tokenUri("https://accounts.google.com/o/oauth2/token")
//            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
//            .userNameAttributeName(IdTokenClaimNames.SUB)
//            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
//            .issuerUri("https://accounts.google.com") // add this line
//            .clientName("Google")
//            .build();
//    }
// 
//}
