package kz.helloapp.model.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin_users")
public class AdminUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String pass;


    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String passs) {
        this.pass = passs;
    }


}
