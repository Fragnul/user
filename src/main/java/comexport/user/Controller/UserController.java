package comexport.user.Controller;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import comexport.user.Model.User;
import comexport.user.Services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Usuários")
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/User/BuscarNome/{nome}")
    @ApiOperation(value="Retorna uma lista de usuários pelo nome")
    public List<User> BuscarUserNome(@PathVariable(value = "nome") String nome){
        return userService.BuscarUserNome(nome);
    }

    @GetMapping("/User/BuscarEmail/{email}")
    @ApiOperation(value="Retorna um usuário pelo email")
    public User BuscarUserEmail(@PathVariable(value = "email") String email){
        return userService.BuscarUserEmail(email);
    }

    @GetMapping("/User/BuscarDtNasc/{dataNascimento}")
    @ApiOperation(value="Retorna uma lista de usuários pela data de nascimento")
    public List<User> BuscarUserDataNascimento(@PathVariable(value = "dataNascimento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataNascimento){
        return userService.BuscarUserDataNascimento(dataNascimento);
    }

    @PostMapping("/User")
    @ApiOperation(value="Salva um usuário")
    public User SalvarUser(@Valid @RequestBody @JsonFormat(timezone = "GMT-3")  User user) {
        return userService.SalvarUser(user);
    }

    @PutMapping("/User")
    @ApiOperation(value="Altera um usuário")
    public User AlterarUser(@Valid @RequestBody @JsonFormat(timezone = "GMT-3")  User user) {
        return userService.SalvarUser(user);
    }

    @DeleteMapping("/User/DeletarId/{id}")
    @ApiOperation(value="Deleta um usuário pelo id")
    public void DeletarUserById(@PathVariable(value = "id") long id){
        userService.DeletarUsuarioId(id);
    }

    @DeleteMapping("/User/DeletarEmail/{email}")
    @ApiOperation(value="Deleta um usuário pelo email")
    public void DeleteUserByEmail(@PathVariable(value = "email") String email){
        userService.DeletarUsuarioEmail(email);
    }
}