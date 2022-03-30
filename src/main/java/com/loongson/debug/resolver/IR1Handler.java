package com.loongson.debug.resolver;

import com.loongson.debug.entity.Command;
import com.loongson.debug.entity.IR1;

public class IR1Handler {

    //IR1[0] 0x3f78d0b0:	mov		eax, esp
    public IR1 handle(String line) {
        /*
         * After split:
         * split[0]:IR1[0] 0x3f78d0b0:
         * split[1]:mov
         * split[3]:eax, esp
         */
        String[] split = line.split("\t");

        /*
         * After split1:
         * split1[0]:IR1[0]
         * split1[1]:0x3f78d0b0
         */
        String[] split1 = split[0].split(" ");
        Command command;
        if (split.length == 2)
            command = new Command(split[1], "", 1);
        else
            command = new Command(split[1], split[3], 1);
        String address = split1[1].substring(0, split1[1].length() - 1);
        String id = split1[0].substring(4, split1[0].length() - 1);

        return new IR1(Integer.parseInt(id), address, command);
    }
}
