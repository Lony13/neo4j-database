package server.Validators;

import server.DAOs.IUserDAO;
import server.DAOs.IUserDAOImpl;
import server.Models.Profile;
import server.Models.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
    private IUserDAO iUserDAO = new IUserDAOImpl();

    @Transactional
    @Override
    public Profile registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException();
        }
        Profile user = new Profile();
        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        if(iUserDAO.createUser(user)!=-1){
            return user;
        } else
            return null;
    }
    private boolean emailExist(String email) {
        return false;
    }
}