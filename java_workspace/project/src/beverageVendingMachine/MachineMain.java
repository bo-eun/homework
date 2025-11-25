package beverageVendingMachine;

import beverageVendingMachine.service.BeverageMachineService;

public class MachineMain {
	public static void main(String[] args) {
		BeverageMachineService machine = new BeverageMachineService();
		
		machine.start();
	}
}
