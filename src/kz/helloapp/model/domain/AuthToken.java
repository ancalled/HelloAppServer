package kz.helloapp.model.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "auth_tokens")
public class AuthToken {

    @Id
    private Long id;

    private String token;

    private Date whenGenerated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getWhenGenerated() {
        return whenGenerated;
    }

    public void setWhenGenerated(Date whenGenerated) {
        this.whenGenerated = whenGenerated;
    }
}
