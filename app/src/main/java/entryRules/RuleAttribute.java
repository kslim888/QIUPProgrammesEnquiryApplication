package entryRules;

import java.util.List;

public class RuleAttribute
{
    private List<String> resultsTypeList, englishTestList, requiredSubjectCreditList, requiredSubjectPassList;
    //numberOfCredit is for SPM and O-Level
    private int numberOfCredit, countSubjectsUEC, countSubjectsSTPM, countSubjectsALevel, countSubjectsSTAM;
    private float minimumCGPA, minimumGP, englishScore;
    private boolean joinProgramme = false;

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

    public List<String> getResultsTypeList() {
        return resultsTypeList;
    }

    public void setResultsTypeList(List<String> resultsTypeList) {
        this.resultsTypeList = resultsTypeList;
    }

    public List<String> getEnglishTestList() {
        return englishTestList;
    }

    public void setEnglishTestList(List<String> englishTestList) {
        this.englishTestList = englishTestList;
    }

    public List<String> getRequiredSubjectCreditList() {
        return requiredSubjectCreditList;
    }

    public void setRequiredSubjectCreditList(List<String> requiredSubjectCreditList) {
        this.requiredSubjectCreditList = requiredSubjectCreditList;
    }

    public List<String> getRequiredSubjectPassList() {
        return requiredSubjectPassList;
    }

    public void setRequiredSubjectPassList(List<String> requiredSubjectPassList) {
        this.requiredSubjectPassList = requiredSubjectPassList;
    }

    public int getNumberOfCredit() {
        return numberOfCredit;
    }

    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
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
}
