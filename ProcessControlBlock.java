public class ProcessControlBlock {

    SimProcess currProcess;
    public SimProcess getCurrProcess() {
        return currProcess;
    }
    public void setCurrProcess(SimProcess currProcess) {
        this.currProcess = currProcess;
    }

    int register1, register2, register3, register4;
    public int getRegister1() {
        return register1;
    }
    public void setRegister1(int newRegister1) {
        this.register1 = newRegister1;
    }
    public int getRegister2() {
        return register2;
    }
    public void setRegister2(int newRegister2) {
        this.register2 = newRegister2;
    }
    public int getRegister3() {
        return register3;
    }
    public void setRegister3(int newRegister3) {
        this.register3 = newRegister3;
    }
    public int getRegister4() {
        return register4;
    }
    public void setRegister4(int newRegister4) {
        this.register4 = newRegister4;
    }

    //keep track of current instruction
    int currInstruction;

    public int getCurrInstruction() {
        return currInstruction;
    }

    public void setCurrInstruction(int currInstruction) {
        this.currInstruction = currInstruction;
    }
}
