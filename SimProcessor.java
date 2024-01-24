import java.util.Random;

public class SimProcessor {

    SimProcess currProcess;
    public SimProcess getCurrProcess() {
        return currProcess;
    }
    public void setCurrProcess(SimProcess currProcess) {
        this.currProcess = currProcess;
    }

    //create registers, getters, and setters
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

    public State executeNextInstruction(SimProcess proc){
        State state = proc.execute(currInstruction);
        currInstruction++;

        //set registers to random integers
        Random rand = new Random();
        int rand_int = rand.nextInt(10000000);
        setRegister1(rand_int);
        rand_int = rand.nextInt(1000000);
        setRegister2(rand_int);
        rand_int = rand.nextInt(1000000);
        setRegister3(rand_int);
        rand_int = rand.nextInt(1000000);
        setRegister4(rand_int);

        return state;


    }
}
