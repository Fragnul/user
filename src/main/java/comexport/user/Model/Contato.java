package comexport.user.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {
    private static  final  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "contato_id")
    private long id;

    private String tipo_contato;

    private String detalhe_contato;

    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    User user;

    public String getTipo_contato() {
        return tipo_contato;
    }

    public void setTipo_contato(String tipo_contato) {
        this.tipo_contato = tipo_contato;
    }

    public String getDetalhe_contato() {
        return detalhe_contato;
    }

    public void setDetalhe_contato(String detalhe_contato) {
        this.detalhe_contato = detalhe_contato;
    }
}
