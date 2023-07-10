package com.sistema.blog.seguridad;


import com.sistema.blog.exceptions.BlogAppException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    
    
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;
    
    //Este método genera el token con el algoritmo y su llave secreta.
    public String generarToken(Authentication authentication) {
        
        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date (fechaActual.getTime() + jwtExpirationInMs);
        
        String token = Jwts.builder().setSubject(username).setIssuedAt
                (new Date()).setExpiration(fechaExpiracion).signWith
                (SignatureAlgorithm.HS512, jwtSecret).compact();  
        return token;
        
    }
    
    //Este método solo obtiene el usuario.
    public String obtenerUsernameDelJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    
    //El siguiente método valida que el token esté correcto, que no esté fallando  demás.
    public boolean validarToken(String token) {
        
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch(SignatureException Ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Firma JWT no valida");
        } catch(MalformedJwtException Ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no valida");
        }catch(ExpiredJwtException Ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT capturadp");
        }catch(UnsupportedJwtException Ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "Token JWT no compatible o soportado");
        }catch(IllegalArgumentException Ex) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "La cadena claims JWT esta vacia");
        }
        
    }
    
}
