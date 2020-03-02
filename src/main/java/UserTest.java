import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	public void user1() {
		User user = User.builder()
				.id(1004l)
				.userName("god")
				.score(70)
				.score(80)
				.build();
		
		System.out.println(user.toString());
	}
}
