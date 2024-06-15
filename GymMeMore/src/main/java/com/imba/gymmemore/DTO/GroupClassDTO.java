package com.imba.gymmemore.DTO;

import com.imba.gymmemore.models.GroupClass;


public class GroupClassDTO {
    private Long id;
    private String className;
    private String scheduledTime;

    public GroupClassDTO(GroupClass groupClass) {
        this.id = groupClass.getId();
        this.className = groupClass.getClassType().getName();
        this.scheduledTime = groupClass.getScheduledTime().toString();
    }

    public GroupClassDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
