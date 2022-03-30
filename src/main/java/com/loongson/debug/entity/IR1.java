package com.loongson.debug.entity;

public class IR1 {
    private int id;
    private String address;
    private Command instruction;

    public IR1(int id, String address, Command instruction) {
        this.id = id;
        this.address = address;
        this.instruction = instruction;
    }

    @Override
    public String toString() {
        return "IR1{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", instruction=" + instruction +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Command getInstruction() {
        return instruction;
    }

    public void setInstruction(Command instruction) {
        this.instruction = instruction;
    }
}
