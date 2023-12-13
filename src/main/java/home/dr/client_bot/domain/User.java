package home.dr.client_bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private long id;

    private long tgId;

    private String name;

//    private boolean isAdmin;

    private String userDetails;
}
