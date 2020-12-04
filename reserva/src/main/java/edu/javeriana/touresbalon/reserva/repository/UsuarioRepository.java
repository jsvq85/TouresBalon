package edu.javeriana.touresbalon.reserva.repository;

import edu.javeriana.touresbalon.reserva.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}