package comexport.user.Services;

import comexport.user.Model.User;
import comexport.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> BuscarUserNome(String nome){
        List<User> users = userRepository.findByNomeUsuario(nome);
        if (users != null){
            for (User user: users) {
                if (user.getNomeUsuario() != null){
                    user.setIdade(CalcularIdade(user));
                }
            }
        }

        return users;
    }

    public User BuscarUserEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user != null){
            if (user.getNomeUsuario() != null){
                user.setIdade(CalcularIdade(user));
            }
        }

        return user;
    }

    public List<User> BuscarUserDataNascimento(Date dataNascimento){
        List<User> users = userRepository.findByDataNascimento(dataNascimento);
        if (users != null){
            for (User user: users) {
                if (user.getNomeUsuario() != null){
                    user.setIdade(CalcularIdade(user));
                }
            }
        }

        return users;
    }

    public User SalvarUser(User user){
        if (ValidarEmail(user)){
            user.setData_registro(new Date());
            user.setData_modificacao(new Date());
            return userRepository.save(user);
        }

        return new User();
    }

    public void DeletarUsuarioId(long id){
        userRepository.removeById(id);
    }

    public void DeletarUsuarioEmail(String email){
        userRepository.removeByEmail(email);
    }

    public boolean ValidarEmail(User user){
        User emailDuplicado = userRepository.findByEmail(user.getEmail());

        if (emailDuplicado != null){
            if (user.getId() == emailDuplicado.getId()){
                return true;
            }
            return false;
        }

        return true;
    }

    public long CalcularIdade(User user){
        Calendar dataNascimento = new GregorianCalendar();

        dataNascimento.setTime(user.getDataNascimento());

        Calendar dateNow = Calendar.getInstance();

        long idade = dateNow.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

        if (dateNow.get(Calendar.MONTH) < dataNascimento.get(Calendar.MONTH) && dateNow.get(Calendar.DAY_OF_MONTH) < dataNascimento.get(Calendar.DAY_OF_MONTH)){
            idade --;
        }

        return idade;
    }
}
