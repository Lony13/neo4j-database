package server.Validators;

import server.Models.Profile;
import server.Models.UserDto;

public interface IUserService {
    Profile registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}