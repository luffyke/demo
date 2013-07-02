package god.damn.it;

public class ColorBall {
    
	private static final int RED_BALL_LENGTH = 33;
	private static final int BLUE_BALL_LENGTH = 16;
	private static final int RESULT_LENGTH = 7;

	public static void main(String[] args) {
		int reds[] = new int[RED_BALL_LENGTH];
		int blues[] = new int[BLUE_BALL_LENGTH];
		int result[] = new int[RESULT_LENGTH];
		
		for (int i = 0; i < RED_BALL_LENGTH; ++i) {
			reds[i] = i + 1;
		}
		for (int i = 0; i < BLUE_BALL_LENGTH; ++i) {
			blues[i] = i + 1;
		}
		
		// red ball
		int index = 0;
		for (int i = 0; i < RESULT_LENGTH - 1; ++i) {
			index = (int) (Math.random() * RED_BALL_LENGTH);
			// Avoid duplicate
			if (reds[index] == 0) {
				// Reselect if duplicate
				i--;
				continue;
			} else {
				result[i] = reds[index];
				// Mark 0 for selected number
				reds[index] = 0;
			}
		}
		
		// blue ball
		result[RESULT_LENGTH - 1] =  blues[(int) (Math.random() * BLUE_BALL_LENGTH)];
		
		String split;
		for (int i = 0; i < RESULT_LENGTH; ++i) {
			split = i == RESULT_LENGTH - 2 ? " - " : " ";
			System.out.print(result[i] + split);
		}
	}
}
