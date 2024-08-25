package lk.ijse.senturaassetment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PictureDTO {
    private Integer id;
    private String name;
    private String media_type;
    private Integer width;
    private Integer height;
    private Integer size;
    private String thumbnail_url;
    private String raw;

}
