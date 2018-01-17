package entryRules;

public class RuleAttribute
{
    private int countCredits, countRequiredSubject, countSPM, countSTPM,
            countSTAM, countALevel, countUEC, countOLevel, countPassScienceSubjects;
    private float minimumCGPA, minimumGP, englishScore;
    private boolean joinProgramme,
            gotMathSubject, gotMathSubjectAndPass, gotMathSubjectAndCredit,
            gotEnglishSubject, gotEnglishSubjectAndPass, gotEnglishSubjectAndCredit,
            gotChemi, gotChemiAndCredit,
            gotBio, gotBioAndCredit,
            gotPhysics, gotPhysicsAndCredit,
            gotScienceSubjectsCredit,
            scienceTechnicalVocationalSubjectsCredit,
            isScienceStream;

    RuleAttribute() {
        joinProgramme = false;
        gotMathSubject = false;
        gotMathSubjectAndPass = false;
        gotMathSubjectAndCredit = false;
        gotEnglishSubject = false;
        gotEnglishSubjectAndPass = false;
        gotEnglishSubjectAndCredit = false;
        gotChemi = false;
        gotChemiAndCredit = false;
        gotBio = false;
        gotBioAndCredit = false;
        gotPhysics = false;
        gotPhysicsAndCredit = false;
        scienceTechnicalVocationalSubjectsCredit = false;
        isScienceStream = false;
        gotScienceSubjectsCredit = false;
        countCredits = 0;
        countRequiredSubject = 0;
        countSTPM = 0;
        countSTAM = 0;
        countALevel = 0;
        countUEC = 0;
        countSPM = 0;
        countOLevel = 0;
        countPassScienceSubjects = 0;
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

    public boolean isGotMathSubject() {
        return gotMathSubject;
    }

    public void setGotMathSubject() {
        this.gotMathSubject = true;
    }

    public boolean isGotMathSubjectAndPass() {
        return gotMathSubjectAndPass;
    }

    public void setGotMathSubjectAndPass() {
        this.gotMathSubjectAndPass = true;
    }

    public boolean isGotMathSubjectAndCredit() {
        return gotMathSubjectAndCredit;
    }

    public void setGotMathSubjectAndCredit() {
        this.gotMathSubjectAndCredit = true;
    }

    public boolean isGotEnglishSubject() {
        return gotEnglishSubject;
    }

    public void setGotEnglishSubject() {
        this.gotEnglishSubject = true;
    }

    public boolean isGotEnglishSubjectAndPass() {
        return gotEnglishSubjectAndPass;
    }

    public void setGotEnglishSubjectAndPass() {
        this.gotEnglishSubjectAndPass = true;
    }

    public boolean isGotEnglishSubjectAndCredit() {
        return gotEnglishSubjectAndCredit;
    }

    public void setGotEnglishSubjectAndCredit() {
        this.gotEnglishSubjectAndCredit = true;
    }

    public boolean isGotChemi() {
        return gotChemi;
    }

    public void setGotChemi() {
        this.gotChemi = true;
    }

    public boolean isGotChemiAndCredit() {
        return gotChemiAndCredit;
    }

    public void setGotChemiAndCredit() {
        this.gotChemiAndCredit = true;
    }

    public boolean isGotBio() {
        return gotBio;
    }

    public void setGotBio() {
        this.gotBio = true;
    }

    public boolean isGotBioAndCredit() {
        return gotBioAndCredit;
    }

    public void setGotBioAndCredit() {
        this.gotBioAndCredit = true;
    }

    public boolean isGotPhysics() {
        return gotPhysics;
    }

    public void setGotPhysics() {
        this.gotPhysics = true;
    }

    public boolean isGotPhysicsAndCredit() {
        return gotPhysicsAndCredit;
    }

    public void setGotPhysicsAndCredit() {
        this.gotPhysicsAndCredit = true;
    }

    public boolean isScienceTechnicalVocationalSubjectsCredit() {
        return scienceTechnicalVocationalSubjectsCredit;
    }

    public void setScienceTechnicalVocationalSubjectsCredit() {
        this.scienceTechnicalVocationalSubjectsCredit = true;
    }

    public boolean isScienceStream() {
        return isScienceStream;
    }

    public void setScienceStreamTrue() {
        isScienceStream = true;
    }

    public int getCountPassScienceSubjects() {
        return countPassScienceSubjects;
    }

    public void incrementCountPassScienceSubjects(int countPassScienceSubjects) {
        this.countPassScienceSubjects += countPassScienceSubjects;
    }

    public boolean isGotScienceSubjectsCredit() {
        return gotScienceSubjectsCredit;
    }

    public void setGotScienceSubjectsCredit() {
        this.gotScienceSubjectsCredit = true;
    }
}
