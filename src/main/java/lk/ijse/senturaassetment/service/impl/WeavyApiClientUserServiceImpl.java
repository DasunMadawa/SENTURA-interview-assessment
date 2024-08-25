package lk.ijse.senturaassetment.service.impl;

import lk.ijse.senturaassetment.dto.UserDTO;
import lk.ijse.senturaassetment.service.WeavyApiClientUserService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeavyApiClientUserServiceImpl implements WeavyApiClientUserService {

    @Value("${api_key}")
    private String apiKey;
    private final OkHttpClient client;

    @Override
    public boolean createUser(UserDTO userDTO) {
        return false;
    }

    @Override
    public UserDTO getUserDetails(String userId) {
        return null;
    }

    @Override
    public List<UserDTO> listUser() throws Exception {
        Request request = new Request.Builder()
                .url("https://b4a86c37cf10442eb928b8e9198520dd.weavy.io/api/users")
                .addHeader("Authorization", "Bearer " + "wys_VejEf8dnBZaxEgmRvaDlSGiScPL64o1m0wTp")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }else {
                System.out.println(response);
            }
            List<UserDTO> users = objectMapper.readValue(responseBody, new TypeReference<List<UserDTO>>() {});
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new Exception("Error");
    }

    @Override
    public boolean updateUser(UserDTO userDTO, String userId) {
        return false;
    }

    @Override
    public boolean deleteUser(String userId) {
        return false;
    }

}
