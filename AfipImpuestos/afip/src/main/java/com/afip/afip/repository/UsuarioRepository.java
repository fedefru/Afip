package com.afip.afip.repository;

import com.afip.afip.dominio.Impuesto;
import com.afip.afip.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByClavefiscalLike(Long clave);
}
