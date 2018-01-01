package entryRules;

public class RuleAttribute
{
    private int countCredits, countRequiredSubject, countSPM, countSTPM,
            countSTAM, countALevel, countUEC, countOLevel;
    private float minimumCGPA, minimumGP, englishScore;
    private boolean joinProgramme;

    RuleAttribute() {
        joinProgramme = false;
        countCredits = 0;
        countRequiredSubject = 0;
        countSTPM = 0;
        countSTAM = 0;
        countALevel = 0;
        countUEC = 0;
        countSPM = 0;
        countOLevel = 0;
    }

    public int getCountUEC() {
        return countUEC;
    }

    public void incrementCountUEC(int countUEC) {
        this.countUEC += countUEC;
    }

    public int getCountALevel() {
        return countALevel;
    }

    public void incrementCountALevel(int countALevel) {
        this.countALevel += countALevel;
    }

    public int getCountSTAM() {
        return countSTAM;
    }

    public void incrementCountSTAM(int countSTAM) {
        this.countSTAM += countSTAM;
    }

    public int getCountSTPM() {
        return countSTPM;
    }

    public void incrementCountSTPM(int countSTPM) {
        this.countSTPM += countSTPM;
    }

    public float getMinimumCGPA() {
        return minimumCGPA;
    }

    public void setMinimumCGPA(float minimumCGPA) {
        this.minimumCGPA = minimumCGPA;
    }

    public float getMinimumGP() {
        return minimumGP;
    }

    public void setMinimumGP(float minimumGP) {
        this.minimumGP = minimumGP;
    }

    public float getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(float englishScore) {
        this.englishScore = englishScore;
    }

    public boolean isJoinProgramme() {
        return joinProgramme;
    }

    public void setJoinProgramme(boolean joinProgramme) {
        this.joinProgramme = joinProgramme;
    }

    public int getCountCredits() {
        return countCredits;
    }

    public void incrementCountCredit(int credit) {
        this.countCredits += credit;
    }

    public int getCountRequiredSubject() {
        return countRequiredSubject;
    }

    public void incrementCountRequiredSubject(int countRequiredSubject) {
        this.countRequiredSubject += countRequiredSubject;
    }

    public int getCountSPM() {
        return countSPM;
    }

    public void incrementCountSPM(int countSPM) {
        this.countSPM += countSPM;
    }

    public int getCountOLevel() {
        return countOLevel;
    }

    public void incrementCountOLevel(int countOLevel) {
        this.countOLevel += countOLevel;
    }
}
