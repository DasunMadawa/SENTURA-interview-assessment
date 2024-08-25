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

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateCustomer(@RequestBody UserDTO userDTO, @PathVariable("id") String id) {

        try {
            weavyApiClientUserService.updateUser(userDTO , id);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);

        } catch (Exception exception ) {
            System.out.println(exception);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getCustomer(@PathVariable("id") String cId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(weavyApiClientUserService.getUserDetails(cId));

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserDTO> deleteCustomer(@PathVariable("id") String userId) {
        try {
            weavyApiClientUserService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

}
