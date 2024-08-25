package lk.ijse.senturaassetment.service;

import lk.ijse.senturaassetment.dto.UserDTO;

import java.util.List;

public interface WeavyApiClientUserService {
    boolean createUser(UserDTO userDTO);
    UserDTO getUserDetails(String userId) throws Exception;
    List<UserDTO> listUser() throws Exception;
    boolean updateUser(UserDTO userDTO , String userId);
    boolean deleteUser(String userId);

}
