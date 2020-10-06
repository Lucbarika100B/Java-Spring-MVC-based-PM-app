
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

@XmlRootElement(name = "project")
@Component
@Entity
@Table(name = "PROJECT")
public class Project {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY, generator = "PROJECT_SEQ")
    @SequenceGenerator(
            name = "PROJECT_SEQ", 
            sequenceName = "PROJECT_SEQ"
        )
    @Column(name = "PROJECT_ID")
    private Integer id;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "MANAGER_ID", insertable=false, updatable=false )
    private Usuario managerId;
    
    @Column(name = "MANAGER_ID")
    private String manager;
    
    @NotNull
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "START_DATE")
    private String startDate;
    
    @Column(name = "END_DATE")
    private String endDate;
    
    @Column(name = "PROJECT_STATUS")
    private String pStatus;
    
    @Column(name="PP_ID")
    private String pooPId;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="PP_ID" , insertable=false, updatable=false)
    private ProjectPool poolP;
    
    @OneToMany (mappedBy = "projectTasks", cascade = CascadeType.ALL)
    private List<Task> ProjectTasks = new ArrayList();

    public Project() {
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPooPId() {
        return pooPId;
    }

    public void setPooPId(String pooPId) {
        this.pooPId = pooPId;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getManagerId() {
        return managerId;
    }

    public void setManagerId(Usuario managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ProjectPool getPoolP() {
        return poolP;
    }

    public void setPoolP(ProjectPool poolP) {
        this.poolP = poolP;
    }

    public List<Task> getProjectTasks() {
        return ProjectTasks;
    }

    public void setProjectTasks(List<Task> ProjectTasks) {
        this.ProjectTasks = ProjectTasks;
        for (Task t : ProjectTasks){
            t.setProjectTasks(this);
        }
    }
    

    
}
