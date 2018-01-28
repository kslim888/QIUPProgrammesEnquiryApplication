package com.qiup.entryrules;

public class RuleAttribute
{
    private int foundationCredit, countRequiredScienceSubject, spmCredit, stpmCredit,
            stamCredit, aLevelCredit, uecCredit, oLevelCredit, countPassScienceSubjects;
    private float minimumCGPA, minimumGP, englishScore;
    private boolean joinProgramme,
            gotMathSubject, gotMathSubjectAndPass, gotMathSubjectAndCredit,
            gotAddMaths,
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
        gotAddMaths = false;
        foundationCredit = 0;
        countRequiredScienceSubject = 0;
        stpmCredit = 0;
        stamCredit = 0;
        aLevelCredit = 0;
        uecCredit = 0;
        spmCredit = 0;
        oLevelCredit = 0;
        countPassScienceSubjects = 0;
    }

    public int getUecCredit() {
        return uecCredit;
    }

    public void incrementUECCredit() {
        this.uecCredit++;
    }

    public int getALevelCredit() {
        return aLevelCredit;
    }

    public void incrementALevelCredit() {
        this.aLevelCredit++;
    }

    public int getStamCredit() {
        return stamCredit;
    }

    public void incrementSTAMCredit() {
        this.stamCredit++;
    }

    public int getStpmCredit() {
        return stpmCredit;
    }

    public void incrementSTPMCredit() {
        this.stpmCredit++;
    }

    public boolean isJoinProgramme() {
        return joinProgramme;
    }

    public void setJoinProgrammeTrue() {
        this.joinProgramme = true;
    }

    int getFoundationCredit() {
        return foundationCredit;
    }

    void incrementFoundationCredit() {
        this.foundationCredit++;
    }

    int getCountRequiredScienceSubject() {
        return countRequiredScienceSubject;
    }

    void incrementCountRequiredScienceSubject() {
        this.countRequiredScienceSubject++;
    }

    int getSpmCredit() {
        return spmCredit;
    }

    void incrementSPMCredit() {this.spmCredit++; }

    int getoLevelCredit() {
        return oLevelCredit;
    }

    void incrementOLevelCredit() {
        this.oLevelCredit++;
    }

    public boolean isGotMathSubject() {
        return gotMathSubject;
    }

    public void setGotMathSubject() {
        this.gotMathSubject = true;
    }

    boolean isGotMathSubjectAndPass() {
        return gotMathSubjectAndPass;
    }

    void setGotMathSubjectAndPass() {
        this.gotMathSubjectAndPass = true;
    }

    public boolean isGotMathSubjectAndCredit() {
        return gotMathSubjectAndCredit;
    }

    public void setGotMathSubjectAndCredit() {
        this.gotMathSubjectAndCredit = true;
    }

    boolean isGotEnglishSubject() {
        return gotEnglishSubject;
    }

    void setGotEnglishSubject() {
        this.gotEnglishSubject = true;
    }

    public boolean isGotEnglishSubjectAndPass() {
        return gotEnglishSubjectAndPass;
    }

    public void setGotEnglishSubjectAndPass() {
        this.gotEnglishSubjectAndPass = true;
    }

    boolean isGotEnglishSubjectAndCredit() {
        return gotEnglishSubjectAndCredit;
    }

    void setGotEnglishSubjectAndCredit() {
        this.gotEnglishSubjectAndCredit = true;
    }

    boolean isGotChemi() {
        return gotChemi;
    }

    void setGotChemi() {
        this.gotChemi = true;
    }

    boolean isGotChemiAndCredit() {
        return gotChemiAndCredit;
    }

    void setGotChemiAndCredit() {
        this.gotChemiAndCredit = true;
    }

    boolean isGotBio() {
        return gotBio;
    }

    void setGotBio() {
        this.gotBio = true;
    }

    boolean isGotBioAndCredit() {
        return gotBioAndCredit;
    }

    void setGotBioAndCredit() {
        this.gotBioAndCredit = true;
    }

    boolean isGotPhysics() {
        return gotPhysics;
    }

    void setGotPhysics() {
        this.gotPhysics = true;
    }

    boolean isGotPhysicsAndCredit() {
        return gotPhysicsAndCredit;
    }

    void setGotPhysicsAndCredit() {
        this.gotPhysicsAndCredit = true;
    }

    boolean isScienceTechnicalVocationalSubjectsCredit() {
        return scienceTechnicalVocationalSubjectsCredit;
    }

    void setScienceTechnicalVocationalSubjectsCredit() {
        this.scienceTechnicalVocationalSubjectsCredit = true;
    }

    boolean isScienceStream() {
        return isScienceStream;
    }

    void setScienceStreamTrue() {
        isScienceStream = true;
    }

    int getCountPassScienceSubjects() {
        return countPassScienceSubjects;
    }

    void incrementCountPassScienceSubjects() {
        this.countPassScienceSubjects++;
    }

    boolean isGotScienceSubjectsCredit() {
        return gotScienceSubjectsCredit;
    }

    void setGotScienceSubjectsCredit() {
        this.gotScienceSubjectsCredit = true;
    }

    boolean isGotAddMaths() {
        return gotAddMaths;
    }

    void setGotAddMaths() {
        this.gotAddMaths = true;
    }
}
