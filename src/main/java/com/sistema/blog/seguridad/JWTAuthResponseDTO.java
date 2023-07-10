package com.sistema.blog.seguridad;


import lombok.Getter;
import lombok.Setter;

public class JWTAuthResponseDTO {

    public JWTAuthResponseDTO(String tokenDeAcceso) {
        super();
        this.tokenDeAcceso = tokenDeAcceso;
    }
    
    public JWTAuthResponseDTO(String tokenDeAcceso, String tipoDeToken) {
        super();
        this.tokenDeAcceso = tokenDeAcceso;
        this.tipoDeToken = tipoDeToken;
    }
    
    @Getter @Setter
    private String tokenDeAcceso;

    @Getter @Setter
    private String tipoDeToken = "Bearer";

}
