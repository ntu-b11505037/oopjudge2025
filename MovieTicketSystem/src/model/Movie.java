package model;

public class Movie {
    private String titleZh;
    private String titleEn;
    private String posterFilename;
    private String classification;
    private String summary;
    private int length;

    public Movie(String titleZh, String titleEn, String posterFilename, String classification, String summary, int length) {
        this.titleZh = titleZh;
        this.titleEn = titleEn;
        this.posterFilename = posterFilename;
        this.classification = classification;
        this.summary = summary;
        this.length = length;
    }

    public String getTitleZh() { return titleZh; }
    public String getTitleEn() { return titleEn; }
    public String getPosterFilename() { return posterFilename; }
    public String getClassification() { return classification; }
    public String getSummary() { return summary; }
    public int getLength() { return length; }

    public int getAgeLimit() {
        try {
            return Integer.parseInt(classification.replaceAll("\\D", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
