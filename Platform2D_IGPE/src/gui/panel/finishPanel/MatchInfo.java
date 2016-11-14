package gui.panel.finishPanel;

public class MatchInfo {

	private String player;
	private String finalTime;
	private int collectedGems = 0;
	
	public MatchInfo(String p, String time, int gems) {
		player = p;
		finalTime = time;
		collectedGems = gems;
	}

	public String getPlayer() {
		return player;
	}

	public String getFinalTime() {
		return finalTime;
	}

	public int getCollectedGems() {
		return collectedGems;
	}

}
