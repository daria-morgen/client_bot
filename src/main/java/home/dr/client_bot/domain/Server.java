package home.dr.client_bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Server {

    private long id;

    private String name;

    private long ownerId;

    private String location;

    private String link;

    private String contact;

    private String about;

}
