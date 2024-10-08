package com.imba.gymmemore.DTO;

import com.imba.gymmemore.models.MembershipType;

public class MembershipTypeDTO {
    private Long id;
    private String name;
    private String description;
    private int price;


    public MembershipTypeDTO() {
    }
    public MembershipType map(){
        return new MembershipType(this.getDescription(),this.getName(), this.getPrice());
    }
    public MembershipTypeDTO(Long id,String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
