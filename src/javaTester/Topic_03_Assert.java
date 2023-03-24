package javaTester;

import org.testng.Assert;

public class Topic_03_Assert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fullName = "Automation Testing";
		//Mong dợi 1 đk trả về là đúng
		Assert.assertTrue(fullName.contains("Auto"));
		
		//Mong dợi 1 đk trả về là sai
		Assert.assertTrue(fullName.contains("Manual"));
		
		//Mong đợi 2 đk bằng nhau
		Assert.assertEquals(fullName, "Automation Testing");
		
	}

}
