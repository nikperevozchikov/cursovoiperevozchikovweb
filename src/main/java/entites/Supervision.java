package entites;

public class Supervision {
    private int modeId;
    private String nameMode;
    private String resultSupervision;

    public Supervision() {
    }

    public Supervision(int modeId, String nameMode, String resultSupervision) {
        this.modeId = modeId;
        this.nameMode = nameMode;
        this.resultSupervision = resultSupervision;
    }

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }

    public String getNameMode() {
        return nameMode;
    }

    public void setNameMode(String nameMode) {
        this.nameMode = nameMode;
    }

    public String getResultSupervision() {
        return resultSupervision;
    }

    public void setResultSupervision(String resultSupervision) {
        this.resultSupervision = resultSupervision;
    }
}
