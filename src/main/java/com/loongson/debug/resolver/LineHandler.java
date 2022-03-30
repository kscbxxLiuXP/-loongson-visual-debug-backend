package com.loongson.debug.resolver;

//单例模式
public class LineHandler {
    private static LineHandler lineHandler;

    //当前解析处于哪个模式
    private int mode;
    //配置颜色校正
    private boolean colorCorrection;

    private LineHandler() {
        colorCorrection = true;
    }

    public static LineHandler getInstance() {
        if (lineHandler == null)
            lineHandler = new LineHandler();
        return lineHandler;
    }

    /*
     * @Description:<br>
     * </>
     * @Param:line 要处理的行
     * @Return:返回当前处理的行是哪一种类型
     * 1:  [LATX]     LATX调试信息
     * 2:  [prologue]
     * 3:  [epilogue]
     * 4:  [fpu rotate]
     * 5:  =、|| TB translation标识 正则: =(.*)\n\|(.*)\n=(.*)\n
     * 6:  tr_init OK. ready to translation.
     * 7:  IR1 num
     * 8:  IR2 num
     * 9:  tr_fini OK. translation done.
     * 10: IR1[***]   x86指令
     * 11: IR2[***]       LoongArch指令
     * 12: 纯loongarch指令
     * 13: [0,0]
     * 14: [*,*] --Label
     */
    public int handleLine(String line) {


        int length = line.length();
        if (line.startsWith("IR1")) {

        } else if (line.startsWith("IR2")) {

        }

        //先解析每行的类型
        if (line.startsWith("tr_init")) {

        }

        return 0;
    }

    public void setColorCorrection(boolean colorCorrection) {
        this.colorCorrection = colorCorrection;
    }

}
