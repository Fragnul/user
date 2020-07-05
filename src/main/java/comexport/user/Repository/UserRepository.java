package comexport.user.Repository;

import comexport.user.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNomeUsuario(String nomeUsuario);

    User findByEmail(String email);

    List<User> findByDataNascimento(Date dataNascimento);

    @Modifying
    @Transactional
    void removeById(long id);

    @Modifying
    @Transactional
    void removeByEmail(String email);
}



