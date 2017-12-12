package entryRules;

public class RuleAttribute
{
    int countCredits, countRequiredSubject;
    float minimumCGPA, minimumGP, englishScore;
    boolean joinProgramme;

    RuleAttribute() {
        joinProgramme = false;
        countCredits = 0;
        countRequiredSubject = 0;
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
}
