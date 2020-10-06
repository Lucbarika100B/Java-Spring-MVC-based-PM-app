
package com.tesisiii.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

@XmlRootElement(name = "projecstPool")
@Component
@Entity
@Table(name = "projectsPool")
public class ProjectPool {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY, generator = "POOL_SEQ")
    @SequenceGenerator(
            name = "POOL_SEQ", 
            sequenceName = "POOL_SEQ"
        )
    @Column(name = "PP_ID" , nullable=false)
    private Integer id;
    
    @Column(name = "USUARIO_ID")
    private String userId;
    
    @Column(name = "PROJECT_ID")
    private String pid;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
    
    @OneToMany (mappedBy = "poolU", cascade = CascadeType.ALL)
    private List<Usuario> usuariosByPool = new ArrayList ();
    
    @OneToMany (mappedBy = "poolP", cascade = CascadeType.ALL)
    private List<Project> projectsByPool = new ArrayList ();

    public ProjectPool() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Usuario> getUsuariosByPool() {
        return usuariosByPool;
    }

    public void setUsuariosByPool(List<Usuario> usuariosByPool) {
        this.usuariosByPool = usuariosByPool;
        for (Usuario u : usuariosByPool){
            u.setPoolU(this);
        }
    }

    public List<Project> getProjectsByPool() {
        return projectsByPool;
    }

    public void setProjectsByPool(List<Project> projectsByPool) {
        this.projectsByPool = projectsByPool;
        for (Project p : projectsByPool){
            p.setPoolP(this);
        }
    }

}
