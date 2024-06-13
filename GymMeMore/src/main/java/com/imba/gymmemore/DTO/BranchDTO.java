package com.imba.gymmemore.DTO;

public class BranchDTO {
    private String name;
    private String description;
    private AddressDTO address;

    public BranchDTO() {
    }

    public BranchDTO(String name, String description, AddressDTO addressDTO) {
        this.name = name;
        this.description = description;
        this.address = addressDTO;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
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
}
