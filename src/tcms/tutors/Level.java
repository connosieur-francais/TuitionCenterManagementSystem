package tcms.tutors;

public class Level {
	private int levelID;
	private String level;
	
	public Level(int levelID, String level) {
		setLevelID(levelID);
		setLevel(level);
	}
	
	public int getLevelID() {
		return levelID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
