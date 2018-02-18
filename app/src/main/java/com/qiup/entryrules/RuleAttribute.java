package com.qiup.entryrules;

import java.util.List;

public class RuleAttribute
{
    private int foundationCredit, countRequiredScienceSubject, spmCredit, stpmCredit,
            stamCredit, aLevelCredit, uecCredit, oLevelCredit, countPassScienceSubjects,
            countCorrectSubjectRequired, countSupportiveSubjectRequired, countScienceSubjectRequired;
    private float minimumCGPA, minimumGP;
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

    // total 14, here 12
    private int amountOfCreditRequired, minimumCreditGrade;
    private boolean gotRequiredSubject, needSupportiveQualification;
    private String whatSupportiveQualification;
    private int minimumRequiredScienceSubject; // for IsScienceStream / NotScienceStream and SPM
    private List<String> subjectRequired; // WhatSubjectRequired
    private List<Integer> minimumGradeRequired; // MinimumSubjectRequiredGrade

    private List<String> supportiveSubjectRequired; // WhatSupportiveSubject
    private List<String> supportiveGradeRequired; // WhatSupportiveGrade

    private List<String> scienceSubjectRequired; // WhatScienceSubjectRequired
    private List<Integer> minimumScienceSubjectGradeRequired; // MinimumScienceSubjectGrade

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

        countCorrectSubjectRequired = 0;
        countSupportiveSubjectRequired = 0;
        countScienceSubjectRequired = 0;
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

    // JSON attribute
    public int getAmountOfCreditRequired() {
        return amountOfCreditRequired;
    }

    public void setAmountOfCreditRequired(int amountOfCreditRequired) {
        this.amountOfCreditRequired = amountOfCreditRequired;
    }

    public int getMinimumCreditGrade() {
        return minimumCreditGrade;
    }

    public void setMinimumCreditGrade(int minimumCreditGrade) {
        this.minimumCreditGrade = minimumCreditGrade;
    }

    public boolean isGotRequiredSubject() {
        return gotRequiredSubject;
    }

    public void setGotRequiredSubject(boolean gotRequiredSubject) {
        this.gotRequiredSubject = gotRequiredSubject;
    }

    public boolean isNeedSupportiveQualification() {
        return needSupportiveQualification;
    }

    public void setNeedSupportiveQualification(boolean needSupportiveQualification) {
        this.needSupportiveQualification = needSupportiveQualification;
    }

    public String getWhatSupportiveQualification() {
        return whatSupportiveQualification;
    }

    public void setWhatSupportiveQualification(String whatSupportiveQualification) {
        this.whatSupportiveQualification = whatSupportiveQualification;
    }

    public int getMinimumRequiredScienceSubject() {
        return minimumRequiredScienceSubject;
    }

    public void setMinimumRequiredScienceSubject(int minimumRequiredScienceSubject) {
        this.minimumRequiredScienceSubject = minimumRequiredScienceSubject;
    }

    public List<String> getSubjectRequired() {
        return subjectRequired;
    }

    public void setSubjectRequired(List<String> subjectRequired) {
        this.subjectRequired = subjectRequired;
    }

    public List<Integer> getMinimumGradeRequired() {
        return minimumGradeRequired;
    }

    public void setMinimumGradeRequired(List<Integer> gradeRequired) {
        this.minimumGradeRequired = gradeRequired;
    }

    public List<String> getSupportiveSubjectRequired() {
        return supportiveSubjectRequired;
    }

    public void setSupportiveSubjectRequired(List<String> supportiveSubjectRequired) {
        this.supportiveSubjectRequired = supportiveSubjectRequired;
    }

    public List<String> getSupportiveGradeRequired() {
        return supportiveGradeRequired;
    }

    public void setSupportiveGradeRequired(List<String> supportiveGradeRequired) {
        this.supportiveGradeRequired = supportiveGradeRequired;
    }

    public List<String> getScienceSubjectRequired() {
        return scienceSubjectRequired;
    }

    public void setScienceSubjectRequired(List<String> scienceSubjectRequired) {
        this.scienceSubjectRequired = scienceSubjectRequired;
    }

    public List<Integer> getMinimumScienceSubjectGradeRequired() {
        return minimumScienceSubjectGradeRequired;
    }

    public void setMinimumScienceSubjectGradeRequired(List<Integer> minimumScienceSubjectGradeRequired) {
        this.minimumScienceSubjectGradeRequired = minimumScienceSubjectGradeRequired;
    }

    // For the length of required things
    public int getCountCorrectSubjectRequired() {
        return countCorrectSubjectRequired;
    }

    public void incrementCountSubjectRequired() {
        this.countCorrectSubjectRequired++;
    }

    public int getCountSupportiveSubjectRequired() {
        return countSupportiveSubjectRequired;
    }

    public void incrementCountSupportiveSubjectRequired() {
        this.countSupportiveSubjectRequired++;
    }

    public int getCountScienceSubjectRequired() {
        return countScienceSubjectRequired;
    }

    public void incrementCountScienceSubjectRequired() {
        this.countScienceSubjectRequired++;
    }
}
