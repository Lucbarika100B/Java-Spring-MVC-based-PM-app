
package com.tesisiii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Component;

@XmlRootElement(name = "task")
@Component
@Entity
@Table(name = "TASK")
public class Task {
    @Id @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "TASK_SEQ")
    @SequenceGenerator(
            name = "TASK_SEQ", 
            sequenceName = "TASK_SEQ"
        )
    @Column(name = "TASK_ID")
    private Integer id;
    
    @Column(name = "TITLE")
    private String title;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "DEADLINE")
    private String deadLine;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "PROJECT_ID")
    private Integer taskProject;
    
    @Column(name = "USER_ID")
    private Integer userTask;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable=false, updatable=false)
    private Usuario usertask;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name ="PROJECT_ID", insertable=false, updatable=false)
    private Project projectTasks;

    public Task() {
    }

    public Integer getUserTask() {
        return userTask;
    }

    public void setUserTask(Integer userTask) {
        this.userTask = userTask;
    }
    
    public Integer getTaskProject() {
        return taskProject;
    }

    public void setTaskProject(Integer taskProject) {
        this.taskProject = taskProject;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUserId() {
        return usertask;
    }

    public void setUserId(Usuario usertask) {
        this.usertask = usertask;
    }

    public Project getProjectId() {
        return projectTasks;
    }

    public void setProjectId(Project projectTasks) {
        this.projectTasks = projectTasks;
    }

    public Usuario getUsertask() {
        return usertask;
    }

    public void setUsertask(Usuario usertask) {
        this.usertask = usertask;
    }

    public Project getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(Project projectTasks) {
        this.projectTasks = projectTasks;
    }


    
}
