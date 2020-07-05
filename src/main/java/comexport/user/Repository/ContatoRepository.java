package comexport.user.Repository;

import comexport.user.Model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository  extends JpaRepository<Contato, Long> {
}
