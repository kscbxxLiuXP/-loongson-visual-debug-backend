package com.loongson.debug.resolver;

import com.loongson.debug.entity.Command;
import com.loongson.debug.entity.IR2;

import java.util.ArrayList;

//IR2[019] at 0xffe8000578 0x50000000 bl        0

public class IR2Handler {


    public IR2 handle(String line) {
        ArrayList<String> list;
        int flag = 0;
        int start = 4;
        String id = "";
        String address = "";
        String instructionHex = "";
        String instruction1 = "";
        String instruction2 = "";
        for (int i = 4; i < line.length(); i++) {
            if (flag >= 4 && line.charAt(i) != ' ') {
                instruction2 = line.substring(i);
                break;
            }
            if (line.charAt(i) == ' ' && flag < 4) {
                //id
                if (flag == 0) {
                    id = line.substring(start, i - 1);
                    i = i + 3;
                    start = i + 1;
                } else if (flag == 1) {
                    //address
                    address = line.substring(start, i);
                    start = i + 1;
                } else if (flag == 2) {
                    //hex
                    instructionHex = line.substring(start, i);
                    start = i + 1;

                } else if (flag == 3) {
                    //instruction
                    instruction1 = line.substring(start, i);

                }
                flag++;
            }

        }


        return new IR2(Integer.parseInt(id), address, instructionHex, new Command(instruction1, instruction2, 2));

    }
}
