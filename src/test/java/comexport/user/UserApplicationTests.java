package comexport.user;

import static org.mockito.Mockito.*;

import comexport.user.Model.Contato;
import comexport.user.Model.User;
import comexport.user.Repository.UserRepository;
import comexport.user.Services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class UserApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    User user = new User();

    List<Contato> contato;

    @Before
    public void setUp() throws Exception{
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
        Date dataNasc = formato.parse("04021997");
        Date dataModReg = formato.parse("04072020");
        user.setId(1);
        user.setContatoList((List<Contato>) contato);
        user.setDataNascimento(dataNasc);
        user.setData_modificacao(dataModReg);
        user.setData_registro(dataModReg);
        user.setEmail("geofragnul@hotmail.com");
        user.setEndereco("Vicente Pereira");
        user.setNomeUsuario("Geovanni");
    }

    @Test
    public void BuscarUserNomeTest(){
        String nome = "Geovanni";

        when(userRepository.findByNomeUsuario(nome))
                .thenReturn(Stream.of(user).collect(Collectors.toList()));
        assertEquals(1, userService.BuscarUserNome(nome).size());
    }

    @Test
    public void BuscarUserEmailTest(){
        String email = "geofragnul@hotmail.com";

        when(userRepository.findByEmail(email))
                .thenReturn(user);
        assertEquals(user, userService.BuscarUserEmail(email));
    }

    @Test
    public void BuscarUserDataNascimentoTest() throws Exception {
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
        Date dataNasc = formato.parse("04021997");
        Date dataModReg = formato.parse("04072020");

        when(userRepository.findByDataNascimento(dataNasc))
                .thenReturn(Stream.of(user).collect(Collectors.toList()));
        assertEquals(1, userService.BuscarUserDataNascimento(dataNasc).size());
    }

    @Test
    public void SalvarUserTest(){
        user.setEmail("geofragnul@hotmail.com");

        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.SalvarUser(user));
    }

    @Test
    public void deletarUserByIdTest(){
        long id = 7;
        userService.DeletarUsuarioId(id);
        verify(userRepository, times(1)).removeById(id);;
    }

    @Test
    public void deleteUserByEmailTest(){
        String email = "geofragnul@hotmail.com";
        userService.DeletarUsuarioEmail(email);
        verify(userRepository, times(1)).removeByEmail(email);;
    }
}
