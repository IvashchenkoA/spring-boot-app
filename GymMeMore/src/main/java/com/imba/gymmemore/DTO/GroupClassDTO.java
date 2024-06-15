package com.imba.gymmemore.DTO;

import com.imba.gymmemore.models.GroupClass;

import java.time.LocalDateTime;

public class GroupClassDTO {
    private Long id;
    private String classType;
    private LocalDateTime scheduledTime;

    public GroupClassDTO(GroupClass groupClass) {
        this.id = groupClass.getId();
        this.classType = groupClass.getClassType().getName();
        this.scheduledTime = groupClass.getScheduledTime();
    }

}
