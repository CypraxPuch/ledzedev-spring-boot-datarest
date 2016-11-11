package com.ledzedev.springboot.datarest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 11/Nov/2016.
 */
@SpringBootApplication
public class LedzedevSpringBootDatarestApplication {

    private final Logger log = LoggerFactory.getLogger(LedzedevSpringBootDatarestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LedzedevSpringBootDatarestApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UsuarioRepositorio ur){
        return  x ->{
            Arrays.asList(
                    new Usuario("Gerardo", 36L, Sexo.M) ,
                    new Usuario("Fidel", 33L, Sexo.M) ,
                    new Usuario("Hugo", 32L, Sexo.M) ,
                    new Usuario("Sarai", 27L, Sexo.F) ,
                    new Usuario("Anallely", 32L, Sexo.F) ,
                    new Usuario("Maribel", 35L, Sexo.F)
            ).forEach(ur::save);

            log.info("Carga inicial en BD -  Hecha.");
        };
    }

}

/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 11/Nov/2016.
 */
@Component
class UsuarioResourceProcessor implements ResourceProcessor<Resource<Usuario>> {

    @Override
    public Resource<Usuario> process(Resource<Usuario> usuarioResource) {
        usuarioResource.add(new Link("http://localhost:8080/images/usuario_"+usuarioResource.getContent().getId() + ".jpg","foto-perfil"));
        return usuarioResource;
    }
}

/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 11/Nov/2016.
 */
@RepositoryRestResource
interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Collection<Usuario> findByNombre(@Param("nom") String nombre);
}

/**
 * Código generado por Gerado Pucheta Figueroa
 * Twitter: @ledzedev
 * http://ledze.mx
 * 11/Nov/2016.
 */
enum Sexo {
    M, F
}

@Entity
class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    private Long edad;

    private Sexo sexo;

    public Usuario() {
    }

    public Usuario(String nombre, Long edad, Sexo sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
