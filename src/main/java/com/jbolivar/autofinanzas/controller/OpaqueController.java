package com.jbolivar.autofinanzas.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpaqueController {

   /* @GetMapping("/opaque")
    public Mono<String> endpointTestOpaque(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal){
        return Mono.just(principal.getAttribute("sub") + " valor");
    }

    @GetMapping("/opaque")
    public Mono<String> endpointTestOpaque(BearerTokenAuthentication authentication){
        return Mono.just(authentication.getTokenAttributes.("sub") + " valor");
    }*/
}
