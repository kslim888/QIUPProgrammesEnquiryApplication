package entryRules;

public class RuleAttribute
{
    public String[] arraysResultsType, arraysEnglishTest;
    public int countCredits, countRequiredSubject, countSubjectsUEC, countSubjectsSTPM, countSubjectsALevel, countSubjectsSTAM;
    public float minimumCGPA, minimumGP, englishScore;
    public boolean joinProgramme;

    RuleAttribute() {
        joinProgramme = false;
        countCredits = 0;
        countRequiredSubject = 0;
    }

    public String[] getResultsTypeList() {
        return arraysResultsType;
    }

    public void setResultsTypeList(String[] resultsTypeList) {
        this.arraysResultsType = resultsTypeList;
    }

    public String[] getEnglishTestList() {
        return arraysEnglishTest;
    }

    public void setEnglishTestList(String[] englishTestList) {
        this.arraysEnglishTest = englishTestList;
    }

    public int getCountSubjectsUEC() {
        return countSubjectsUEC;
    }

    public void setCountSubjectsUEC(int countSubjectsUEC) {
        this.countSubjectsUEC = countSubjectsUEC;
    }

    public int getCountSubjectsSTPM() {
        return countSubjectsSTPM;
    }

    public void setCountSubjectsSTPM(int countSubjectsSTPM) {
        this.countSubjectsSTPM = countSubjectsSTPM;
    }

    public int getCountSubjectsALevel() {
        return countSubjectsALevel;
    }

    public void setCountSubjectsALevel(int countSubjectsALevel) {
        this.countSubjectsALevel = countSubjectsALevel;
    }

    public int getCountSubjectsSTAM() {
        return countSubjectsSTAM;
    }

    public void setCountSubjectsSTAM(int countSubjectsSTAM) {
        this.countSubjectsSTAM = countSubjectsSTAM;
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
