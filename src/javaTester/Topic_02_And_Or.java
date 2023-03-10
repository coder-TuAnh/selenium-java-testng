package javaTester;

public class Topic_02_And_Or {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean statusA;
		boolean statusB;
		
		//And - &&
		//Neu 1 trong 2 dk sai -> kq sai
		//Neu 1 trong 2 dk dung -> kq sai
		//ca 2 dk sai -> kq sai
		//ca 2 dk dung -> kq dung
		statusA = true;
		statusB = false;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		//Or - ||
		//Nếu 1 trong 2 đk sai -> kq đúng
		//Nếu 1 trong 2 đk đúng -> kq đúng
		//cả 2 đk sai -> kq sai
		//cả 2 đk đúng -> kq đúng
		statusA = true;
		statusB = false;
		System.out.println("Kết quả = " + (statusA || statusB));
		
	}

}
