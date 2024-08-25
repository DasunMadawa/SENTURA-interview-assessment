package lk.ijse.senturaassetment;

import okhttp3.OkHttpClient;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SenturaAssetmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenturaAssetmentApplication.class, args);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
