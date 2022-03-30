package com.loongson.debug.entity;

public class IR2 {
    private int id;
    private String address;
    private String instructionHex;
    private Command instruction;


    public IR2(int id, String address, String instructionHex, Command instruction) {
        this.id = id;
        this.address = address;
        this.instructionHex = instructionHex;
        this.instruction = instruction;
    }


    @Override
    public String toString() {
        return "IR2{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", instructionHex='" + instructionHex + '\'' +
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

    public String getInstructionHex() {
        return instructionHex;
    }

    public void setInstructionHex(String instructionHex) {
        this.instructionHex = instructionHex;
    }

    public Command getInstruction() {
        return instruction;
    }

    public void setInstruction(Command instruction) {
        this.instruction = instruction;
    }
}
