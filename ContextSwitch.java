import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContextSwitch {
    public static void main(String args[]) {
        final int QUANTUM = 5;
        int quantumCount = 0;

        //create 10 processors and PCBs
        SimProcessor processor = new SimProcessor();
        SimProcess Proc1 = new SimProcess(1, 111, "Proc1");
        ProcessControlBlock PCB1 = new ProcessControlBlock();
        SimProcess Proc2 = new SimProcess(2, 222, "Proc2");
        ProcessControlBlock PCB2 = new ProcessControlBlock();
        SimProcess Proc3 = new SimProcess(3, 333, "Proc3");
        ProcessControlBlock PCB3 = new ProcessControlBlock();
        SimProcess Proc4 = new SimProcess(4, 144, "Proc4");
        ProcessControlBlock PCB4 = new ProcessControlBlock();
        SimProcess Proc5 = new SimProcess(5, 255, "Proc5");
        ProcessControlBlock PCB5 = new ProcessControlBlock();
        SimProcess Proc6 = new SimProcess(6, 366, "Proc6");
        ProcessControlBlock PCB6 = new ProcessControlBlock();
        SimProcess Proc7 = new SimProcess(7, 177, "Proc7");
        ProcessControlBlock PCB7 = new ProcessControlBlock();
        SimProcess Proc8 = new SimProcess(8, 288, "Proc8");
        ProcessControlBlock PCB8 = new ProcessControlBlock();
        SimProcess Proc9 = new SimProcess(9, 399, "Proc9");
        ProcessControlBlock PCB9 = new ProcessControlBlock();
        SimProcess Proc10 = new SimProcess(10, 250, "Proc10");
        ProcessControlBlock PCB10 = new ProcessControlBlock();

        //create ArrayLists of Ready and Blocked Processors
        List<SimProcess> blockedProc = new ArrayList<>();
        List<SimProcess> readyProcList = new ArrayList<>();
        readyProcList.add(Proc1);
        readyProcList.add(Proc2);
        readyProcList.add(Proc3);
        readyProcList.add(Proc4);
        readyProcList.add(Proc5);
        readyProcList.add(Proc6);
        readyProcList.add(Proc7);
        readyProcList.add(Proc8);
        readyProcList.add(Proc9);
        readyProcList.add(Proc10);

        //create ArrayLis of PCBs
        List<ProcessControlBlock> PCBList = new ArrayList<>();
        PCBList.add(PCB1);
        PCBList.add(PCB2);
        PCBList.add(PCB3);
        PCBList.add(PCB4);
        PCBList.add(PCB5);
        PCBList.add(PCB6);
        PCBList.add(PCB7);
        PCBList.add(PCB8);
        PCBList.add(PCB9);
        PCBList.add(PCB10);


        //initialize current process & PCB to Process 1
        SimProcess currentProcess = Proc1;
        ProcessControlBlock currentPCB = PCB1;


        for (int i = 0; i < 3000; i++) {

            if(readyProcList.size() == 0) {
                System.out.println("Ready List Empty. Unblocking Processes.");
                while (readyProcList.size() == 0) {
                    unblock(blockedProc, readyProcList);
                }
            }

            System.out.print("Step: "+ i +" |\t");

            //executes the instruction
            processor.setCurrInstruction(currentPCB.getCurrInstruction());
            State state = processor.executeNextInstruction(currentProcess);

            switch (state) {
                case BLOCKED:
                    blockedProc.add(currentProcess);
                    readyProcList.remove(0);
                    quantumCount = 0;
                    currentPCB.setCurrInstruction(processor.getCurrInstruction());
                    System.out.println("***" + currentProcess.getPocName() + " Blocked***");
                    System.out.print("Step: "+ ++i +" |\t");
                    ContextSwitchSave(currentProcess, currentPCB, processor);

                    currentProcess = readyProcList.get(0);
                    currentPCB = setPCB( PCBList, currentProcess);
                    System.out.print("Step: "+ ++i +" |\t");
                    ContextSwitchRestore(currentProcess, currentPCB);
                    break;

                case FINISHED:
                    readyProcList.remove(0);
                    quantumCount = 0;
                    System.out.print("Step: "+ ++i +" |\t");
                    System.out.println("***" + currentProcess.getPocName() + " Completed***");

                    currentProcess = readyProcList.get(0);
                    currentPCB = setPCB( PCBList, currentProcess);
                    System.out.print("Step: "+ ++i +" |\t");
                    ContextSwitchRestore(currentProcess, currentPCB);
                    break;

                case READY:
                    currentPCB.setCurrInstruction(processor.getCurrInstruction());
                    quantumCount++;

                    if (quantumCount == QUANTUM) {
                        readyProcList.remove(0);
                        readyProcList.add(currentProcess);
                        quantumCount = 0;

                        System.out.println("***Quantum Expired***");
                        System.out.print("Step: "+ ++i +" |\t");
                        ContextSwitchSave(currentProcess, currentPCB, processor);

                        currentProcess = readyProcList.get(0);
                        currentPCB = setPCB(PCBList, currentProcess);
                        System.out.print("Step: "+ ++i +" |\t");
                        ContextSwitchRestore(currentProcess, currentPCB);

                    }
            }
            unblock(blockedProc, readyProcList);

        }


    }

    //30% chance of a process getting unblocked
    private static void unblock (List < SimProcess > blockedProc, List < SimProcess > readyProcList){
        Random rand = new Random();
        List<SimProcess> unblockProc = new ArrayList<>();

        //adds processes that need to unblock to a list
        for (int ix = 0; ix < blockedProc.size(); ix++) {
            int rand_int = rand.nextInt(100);
            if (rand_int < 30) {
                unblockProc.add(blockedProc.get(ix));
            }
        }

        //removes them from blocked list & adds them to ready list
        for (int ix = 0; ix < unblockProc.size(); ix++) {
            blockedProc.remove(unblockProc.get(ix));
            readyProcList.add(unblockProc.get(ix));
        }

        unblockProc.clear();
    }
    private static ProcessControlBlock setPCB(List<ProcessControlBlock> PCBList, SimProcess currentProcess) {
        ProcessControlBlock cPCB = null;
        switch (currentProcess.getPid()) {
            case 1 -> cPCB = PCBList.get(0); //PCB1
            case 2 -> cPCB = PCBList.get(1); //PCB2
            case 3 -> cPCB = PCBList.get(2);
            case 4 -> cPCB = PCBList.get(3);
            case 5 -> cPCB = PCBList.get(4);
            case 6 -> cPCB = PCBList.get(5);
            case 7 -> cPCB = PCBList.get(6);
            case 8 -> cPCB = PCBList.get(7);
            case 9 -> cPCB = PCBList.get(8);
            case 10 -> cPCB = PCBList.get(9);
        }

        return cPCB;
    }
    private static void ContextSwitchSave(SimProcess currentProcess, ProcessControlBlock currentPCB, SimProcessor processor) {
        currentPCB.setRegister1(processor.getRegister1());
        currentPCB.setRegister2(processor.getRegister2());
        currentPCB.setRegister3(processor.getRegister3());
        currentPCB.setRegister4(processor.getRegister4());
        System.out.println("CONTEXT SWITCH: Saving "+ currentProcess.getPocName());
        System.out.println("Instruction Number: " + currentPCB.getCurrInstruction()
                + "\tR1: " + currentPCB.getRegister1() + "\tR2: " + currentPCB.getRegister2()
                + "\tR3: " + currentPCB.getRegister3() + "\tR4: " + currentPCB.getRegister4());    }

        private static void ContextSwitchRestore(SimProcess currentProcess, ProcessControlBlock currentPCB) {
        System.out.println("CONTEXT SWITCH: Restoring " + currentProcess.getPocName());
            System.out.println("Instruction Number: " + currentPCB.getCurrInstruction()
                + "\tR1: " + currentPCB.getRegister1() + "\tR2: " + currentPCB.getRegister2()
                + "\tR3: " + currentPCB.getRegister3() + "\tR4: " + currentPCB.getRegister4());

        }
}


