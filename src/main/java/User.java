import java.util.List;

import lombok.Builder;
import lombok.Singular;

@Builder
public class User {
	private Long id;
	private String userName;
	@Singular("score")
	private List<Integer> scores;

}
