package lk.ijse.senturaassetment.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lk.ijse.senturaassetment.dto.UserDTO;
import lk.ijse.senturaassetment.service.WeavyApiClientUserService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeavyApiClientUserServiceImpl implements WeavyApiClientUserService {

    @Value("${api_key}")
    private String apiKey;
    private final OkHttpClient client;
    private final ModelMapper modelMapper;


    @Override
    public boolean createUser(UserDTO userDTO) {
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);

        RequestBody body = RequestBody.create(
                json, okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://b4a86c37cf10442eb928b8e9198520dd.weavy.io/api/users")
                .addHeader("Authorization", "Bearer " + "wys_VejEf8dnBZaxEgmRvaDlSGiScPL64o1m0wTp")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("User created successfully.");
                return true;
            } else {
                System.err.println("Failed to create user: " + response.code() + " - " + response.message());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public UserDTO getUserDetails(String userId) throws Exception {
        Request request = new Request.Builder()
                .url("https://b4a86c37cf10442eb928b8e9198520dd.weavy.io/api/users/" + userId)
                .addHeader("Authorization", "Bearer " + "wys_VejEf8dnBZaxEgmRvaDlSGiScPL64o1m0wTp")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();

            Gson gson = new Gson();
            return gson.fromJson(responseBody, UserDTO.class);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error fetching user details: " + e.getMessage());
        }

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
            }

            String responseBody = response.body().string();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

            if (jsonObject.get("count").getAsInt() == 0) {

                return new ArrayList<>();
            }

            List<UserDTO> userDTOList = gson.fromJson(jsonObject.get("data"), new TypeToken<List<UserDTO>>() {}.getType());
            System.out.println(userDTOList);

            return modelMapper.map(userDTOList, new TypeToken<List<UserDTO>>() {}.getType());

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Error");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error");
        }
    }

    @Override
    public boolean updateUser(UserDTO userDTO, String userId) {
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);

        RequestBody body = RequestBody.create(
                json, okhttp3.MediaType.parse("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://b4a86c37cf10442eb928b8e9198520dd.weavy.io/api/users/"+userId)
                .addHeader("Authorization", "Bearer " + "wys_VejEf8dnBZaxEgmRvaDlSGiScPL64o1m0wTp")
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("User updated successfully.");
                return true;
            } else {
                System.err.println("Failed to update user: " + response.code() + " - " + response.message());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteUser(String userId) {
        Request request = new Request.Builder()
                .url("https://b4a86c37cf10442eb928b8e9198520dd.weavy.io/api/users/" + userId + "/trash")
                .post(RequestBody.create(null, ""))
                .addHeader("Authorization", "Bearer " + "wys_VejEf8dnBZaxEgmRvaDlSGiScPL64o1m0wTp")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
