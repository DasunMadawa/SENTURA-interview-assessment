package lk.ijse.senturaassetment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String uid;
    private String display_name;
    private String avatar_url;
    private String email;
    private String given_name;
    private String middle_name;
    private String name;
    private String family_name;
    private String nickname;
    private String phone_number;
    private String comment;
    private PictureDTO picture;
    private Map<String, Object> metadata;
    private List<String> tags;
    private String presence;
    private Integer directory_id;
    private DirectoryDTO directory;
    private String created_at;
    private String updated_at;
    private Boolean is_bot;
    private Boolean is_suspended;
    private Boolean is_trashed;

}
