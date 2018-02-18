package com.qiup.entryrules;

import android.util.Log;

import com.qiup.POJO.RulePojo;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "FIB", description = "Entry rule to join Foundation in Business")
public class FIB {
    private static RuleAttribute fibRuleAttribute;

    public FIB() {
        fibRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects") String[] studentSubjects,
                               @Fact("Student's Grades") int[] studentGrades) {
        setJSONAttribute(qualificationLevel); // First set json attribute to the rule

        // All qualification check grade is Credit or not.
        // Only credit then increment. If credit not enough then return false
        switch (qualificationLevel) {
            case "SPM": {
                // First check got required subject or not
                if (fibRuleAttribute.isGotRequiredSubject()) {
                    for (int i = 0; i < studentSubjects.length; i++) {
                        for (int j = 0; j < fibRuleAttribute.getSubjectRequired().size(); j++) {
                            if (Objects.equals(studentSubjects[i], fibRuleAttribute.getSubjectRequired().get(j))) {
                                if (studentGrades[i] <= fibRuleAttribute.getMinimumGradeRequired().get(j)) {
                                    fibRuleAttribute.incrementCountSubjectRequired();
                                }
                            }
                        }
                    }
                }

                // For every grade, check whether the grade is larger or equal to minimum credit grade
                // Smaller the number the better the grade
                for (int i = 0; i < studentGrades.length; i++) {
                    if (studentGrades[i] <= fibRuleAttribute.getMinimumCreditGrade())
                        fibRuleAttribute.incrementFoundationCredit();
                }

                if (fibRuleAttribute.isGotRequiredSubject()) {
                    if (fibRuleAttribute.getFoundationCredit() < fibRuleAttribute.getAmountOfCreditRequired()
                            && fibRuleAttribute.getCountCorrectSubjectRequired() != fibRuleAttribute.getSubjectRequired().size()) {
                        return false;
                    }
                } else {
                    if (fibRuleAttribute.getFoundationCredit() < fibRuleAttribute.getAmountOfCreditRequired())
                        return false;
                }

            }
            break;
            case "O-Level": {
                // First check got required subject or not
                if (fibRuleAttribute.isGotRequiredSubject()) {
                    for (int i = 0; i < studentSubjects.length; i++) {
                        for (int j = 0; j < fibRuleAttribute.getSubjectRequired().size(); j++) {
                            if (Objects.equals(studentSubjects[i], fibRuleAttribute.getSubjectRequired().get(j))) {
                                if (studentGrades[i] <= fibRuleAttribute.getMinimumGradeRequired().get(j)) {
                                    fibRuleAttribute.incrementCountSubjectRequired();
                                }
                            }
                        }
                    }
                }

                // For every grade, check whether the grade is larger or equal to minimum credit grade
                // Smaller the number the better the grade
                for (int i = 0; i < studentGrades.length; i++) {
                    if (studentGrades[i] <= fibRuleAttribute.getMinimumCreditGrade())
                        fibRuleAttribute.incrementFoundationCredit();
                }

                if (fibRuleAttribute.isGotRequiredSubject()) {
                    if (fibRuleAttribute.getFoundationCredit() < fibRuleAttribute.getAmountOfCreditRequired()
                            && fibRuleAttribute.getCountCorrectSubjectRequired() != fibRuleAttribute.getSubjectRequired().size()) {
                        return false;
                    }
                } else {
                    if (fibRuleAttribute.getFoundationCredit() < fibRuleAttribute.getAmountOfCreditRequired())
                        return false;
                }
            }
            break;
            case "UEC": {
                // First check got required subject or not
                if (fibRuleAttribute.isGotRequiredSubject()) {
                    for (int i = 0; i < studentSubjects.length; i++) {
                        for (int j = 0; j < fibRuleAttribute.getSubjectRequired().size(); j++) {
                            if (Objects.equals(studentSubjects[i], fibRuleAttribute.getSubjectRequired().get(j))) {
                                if (studentGrades[i] <= fibRuleAttribute.getMinimumGradeRequired().get(j)) {
                                    fibRuleAttribute.incrementCountSubjectRequired();
                                }
                            }
                        }
                    }
                }

                // For every grade, check whether the grade is larger or equal to minimum credit grade
                // Smaller the number the better the grade
                for (int i = 0; i < studentGrades.length; i++) {
                    if (studentGrades[i] <= fibRuleAttribute.getMinimumCreditGrade())
                        fibRuleAttribute.incrementFoundationCredit();
                }

                if (fibRuleAttribute.isGotRequiredSubject()) {
                    if (fibRuleAttribute.getFoundationCredit() < fibRuleAttribute.getAmountOfCreditRequired()
                            && fibRuleAttribute.getCountCorrectSubjectRequired() != fibRuleAttribute.getSubjectRequired().size()) {
                        return false;
                    }
                } else {
                    if (fibRuleAttribute.getFoundationCredit() < fibRuleAttribute.getAmountOfCreditRequired())
                        return false;
                }
            }
        }
        // If requirements is satiafied, return true
        return true;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        fibRuleAttribute.setJoinProgrammeTrue();
        Log.d("FIB joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return fibRuleAttribute.isJoinProgramme();
    }

    private void setJSONAttribute(String qualificationLevel)
    {
        switch(qualificationLevel)
        {
            case "SPM":
            {
                fibRuleAttribute.setAmountOfCreditRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getSPM().getAmountOfCreditRequired());
                fibRuleAttribute.setMinimumCreditGrade(RulePojo.getRulePojo().getAllProgramme().getFib().getSPM().getMinimumCreditGrade());
                fibRuleAttribute.setGotRequiredSubject(RulePojo.getRulePojo().getAllProgramme().getFib().getSPM().isGotRequiredSubject());
                if(fibRuleAttribute.isGotRequiredSubject())
                {
                    fibRuleAttribute.setSubjectRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getSPM().getWhatSubjectRequired().getSubject());
                    fibRuleAttribute.setMinimumGradeRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getSPM().getMinimumSubjectRequiredGrade().getGrade());
                }
            }
            break;
            case "O-Level":
            {
                fibRuleAttribute.setAmountOfCreditRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getOLevel().getAmountOfCreditRequired());
                fibRuleAttribute.setMinimumCreditGrade(RulePojo.getRulePojo().getAllProgramme().getFib().getOLevel().getMinimumCreditGrade());
                fibRuleAttribute.setGotRequiredSubject(RulePojo.getRulePojo().getAllProgramme().getFib().getOLevel().isGotRequiredSubject());
                if(fibRuleAttribute.isGotRequiredSubject())
                {
                    fibRuleAttribute.setSubjectRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getOLevel().getWhatSubjectRequired().getSubject());
                    fibRuleAttribute.setMinimumGradeRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getOLevel().getMinimumSubjectRequiredGrade().getGrade());
                }
            }
            break;
            case "UEC":
            {
                fibRuleAttribute.setAmountOfCreditRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getUEC().getAmountOfCreditRequired());
                fibRuleAttribute.setMinimumCreditGrade(RulePojo.getRulePojo().getAllProgramme().getFib().getUEC().getMinimumCreditGrade());
                fibRuleAttribute.setGotRequiredSubject(RulePojo.getRulePojo().getAllProgramme().getFib().getUEC().isGotRequiredSubject());
                if(fibRuleAttribute.isGotRequiredSubject())
                {
                    fibRuleAttribute.setSubjectRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getUEC().getWhatSubjectRequired().getSubject());
                    fibRuleAttribute.setMinimumGradeRequired(RulePojo.getRulePojo().getAllProgramme().getFib().getUEC().getMinimumSubjectRequiredGrade().getGrade());
                }
            }
        }
    }
}
