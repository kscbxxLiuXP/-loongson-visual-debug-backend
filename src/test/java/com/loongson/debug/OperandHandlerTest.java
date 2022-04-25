package com.loongson.debug;

import com.loongson.debug.resolver.OperandHandler;
import org.junit.jupiter.api.Test;

public class OperandHandlerTest {
    OperandHandler operandHandler = new OperandHandler();

    @Test
    void test() {
        String[] operands = {
                "ebp, ebp",
                "0x4ce7d0",
                "[rax]",
                "word ptr []",
                "word ptr fs:[]",
                ""

        };
        for (String operand : operands) {
            String resolve = operandHandler.pattern(operand);
            System.out.println(operand + "->" + resolve);
        }

    }
}
