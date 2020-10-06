
package com.tesisiii.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

@XmlRootElement(name = "users")
@Component
@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(
            name = "USUARIO_SEQ", 
            sequenceName = "USUARIO_SEQ"
        )
    @Column(name = "USUARIO_ID")
    private Integer id;
    
    @NotNull
    @Column(name = "LOGIN")
    private String login;
    
    @NotNull
    @Column(name = "PASSWORD")
    private String password;
    
    @NotNull
    @Column(name = "STATUS")
    private String status;
    
    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;
    
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "PROFILE")
    private String profile;
    
    @Column(name = "MANAGER_ID")
    private String managerId;
    
    @Column(name = "PP_ID")
    private String pooPId;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="USUARIO_ID", insertable=false, updatable=false)
    private Usuario manager;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name ="PP_ID", insertable=false, updatable=false)
    private ProjectPool poolU;
    
    @OneToMany  (mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Usuario> usuariosByManager ;
    
    @OneToMany (mappedBy = "usertask", cascade = CascadeType.ALL)
    private List<Task> usuariosTasks = new ArrayList();
    
    @OneToMany (mappedBy = "managerId", cascade = CascadeType.ALL)
    private List<Project> ProjectByManager = new ArrayList();
    
    
    public Usuario() {
    }

    public String getPooPId() {
        return pooPId;
    }

    public void setPooPId(String pooPId) {
        this.pooPId = pooPId;
    }
    
    
    
    public List<Project> getProjectByManager() {
        return ProjectByManager;
    }

    public void setProjectByManager(List<Project> ProjectByManager) {
        this.ProjectByManager = ProjectByManager;
    }
    
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Usuario getManager() {
        return manager;
    }

    public void setManager(Usuario manager) {
        this.manager = manager;
    }

    public List<Usuario> getUsuariosByManager() {
        return usuariosByManager;
    }

    public void setUsuariosByManager(List<Usuario> usuariosByManager) {
        this.usuariosByManager = usuariosByManager;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public ProjectPool getPoolU() {
        return poolU;
    }

    public void setPoolU(ProjectPool poolU) {
        this.poolU = poolU;
    }

    public List<Task> getUsuariosTasks() {
        return usuariosTasks;
    }

    public void setUsuariosTasks(List<Task> usuariosTasks) {
        this.usuariosTasks = usuariosTasks;
        for (Task t : usuariosTasks){
            t.setUsertask(this);
        }
    }
    

    
}
