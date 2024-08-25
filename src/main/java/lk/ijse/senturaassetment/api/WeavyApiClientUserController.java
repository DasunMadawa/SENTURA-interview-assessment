package lk.ijse.senturaassetment.api;

import lk.ijse.senturaassetment.dto.UserDTO;
import lk.ijse.senturaassetment.service.WeavyApiClientUserService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class WeavyApiClientUserController {
    private final WeavyApiClientUserService weavyApiClientUserService;

    @GetMapping("/health")
    public String customerHealthCheck() {
        return "User OK";

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        try {
//            System.out.println(customerDTO);
            weavyApiClientUserService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);

        } catch (Exception exception){
//            System.out.println(duplicateException.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> listUsers() {
        try {
            return ResponseEntity.ok(weavyApiClientUserService.listUser());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
