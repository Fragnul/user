package comexport.user.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuario")
public class User implements Serializable {

    private static  final  long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment",strategy = "increment")
    @Column(name = "usuario_id")
    private long id;

    @Column(name = "nome_usuario",nullable = false, updatable= false)
    @NotBlank(message = "{nomeUsuario.not.blank}}")
    private String nomeUsuario;

    @Column(nullable = false)
    @NotBlank(message = "{email.not.blank}}")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento", nullable = false)
    @NotBlank(message = "{dataNascimento.not.blank}")
    @ApiModelProperty(dataType = "java.sql.Date")
    @JsonFormat(timezone = "GMT-3")
    private Date dataNascimento;

    @Transient
    @ApiModelProperty(hidden = true)
    private long idade;

    private String endereco;

    @OneToMany(fetch = FetchType.LAZY,mappedBy="usuario")
    private List<Contato> contatoList=new ArrayList<Contato>();

    @ApiModelProperty(hidden = true)
    @JsonFormat(timezone = "GMT-3")
    private Date data_registro;

    @ApiModelProperty(hidden = true)
    @JsonFormat(timezone = "GMT-3")
    private Date data_modificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public long getIdade() {
        return idade;
    }

    public void setIdade(long idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    public Date getData_registro() {
        return data_registro;
    }

    public void setData_registro(Date data_registro) {
        this.data_registro = data_registro;
    }

    public Date getData_modificacao() {
        return data_modificacao;
    }

    public void setData_modificacao(Date data_modificacao) {
        this.data_modificacao = data_modificacao;
    }
}
