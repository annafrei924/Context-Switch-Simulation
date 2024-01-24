import java.util.Random;
public class SimProcess {
    int pid, totalInstructions;
    String pocName;

    SimProcess(int pid, int totalInstructions, String pocName){
        this.pid = pid;
        this.totalInstructions = totalInstructions;
        this.pocName = pocName;
    }
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTotalInstructions() {
        return totalInstructions;
    }

    public void setTotalInstructions(int totalInstructions) {
        this.totalInstructions = totalInstructions;
    }

    public String getPocName() {
        return pocName;
    }

    public void setPocName(String pocName) {
        this.pocName = pocName;
    }




    State execute(int instructionNumber) {
        //displays the message
        System.out.println("PID: " + pid + "\tName: " + pocName + "\tExecuting Instruction: "+ instructionNumber);

        //checks if the process is finished
        if (instructionNumber>=totalInstructions) {
            return State.FINISHED;

        //15% chance of blocking or returns ready
        } else {
            Random rand = new Random();
            int rand_int = rand.nextInt(100);
            if (rand_int < 15) {
                return State.BLOCKED;

            } else {
                return State.READY;
            }
        }
    }
}
