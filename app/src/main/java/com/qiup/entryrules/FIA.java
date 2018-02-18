package com.qiup.entryrules;

import android.util.Log;

import com.qiup.POJO.RulePojo;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "FIA", description = "Entry rule to join Foundation in Arts")
public class FIA
{
    private static RuleAttribute fiaRuleAttribute;

    public FIA() { fiaRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects") String[] studentSubjects,
                               @Fact("Student's Grades") int[] studentGrades)
    {
        setJSONAttribute(qualificationLevel); // First set json attribute to the rule

        // All qualification check grade is Credit or not.
        // Only credit then increment. If credit not enough then return false
        switch(qualificationLevel)
        {
            case "SPM":
            {
                // First check got required subject or not
                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    for(int i = 0; i < studentSubjects.length; i++)
                    {
                        for(int j = 0; j < fiaRuleAttribute.getSubjectRequired().size(); j++)
                        {
                            if(Objects.equals(studentSubjects[i], fiaRuleAttribute.getSubjectRequired().get(j)))
                            {
                                if(studentGrades[i] <= fiaRuleAttribute.getMinimumGradeRequired().get(j))
                                {
                                    fiaRuleAttribute.incrementCountSubjectRequired();
                                }
                            }
                        }
                    }
                }

                // For every grade, check whether the grade is larger or equal to minimum credit grade
                // Smaller the number the better the grade
                for(int i = 0; i < studentGrades.length; i++)
                {
                    if(studentGrades[i] <= fiaRuleAttribute.getMinimumCreditGrade())
                        fiaRuleAttribute.incrementFoundationCredit();
                }

                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    if(fiaRuleAttribute.getFoundationCredit() < fiaRuleAttribute.getAmountOfCreditRequired()
                            && fiaRuleAttribute.getCountCorrectSubjectRequired() != fiaRuleAttribute.getSubjectRequired().size())
                    {
                        return false;
                    }
                }
                else
                {
                    if(fiaRuleAttribute.getFoundationCredit() < fiaRuleAttribute.getAmountOfCreditRequired())
                        return false;
                }

            }
            break;
            case "O-Level":
            {
                // First check got required subject or not
                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    for(int i = 0; i < studentSubjects.length; i++)
                    {
                        for(int j = 0; j < fiaRuleAttribute.getSubjectRequired().size(); j++)
                        {
                            if(Objects.equals(studentSubjects[i], fiaRuleAttribute.getSubjectRequired().get(j)))
                            {
                                if(studentGrades[i] <= fiaRuleAttribute.getMinimumGradeRequired().get(j))
                                {
                                    fiaRuleAttribute.incrementCountSubjectRequired();
                                }
                            }
                        }
                    }
                }

                // For every grade, check whether the grade is larger or equal to minimum credit grade
                // Smaller the number the better the grade
                for(int i = 0; i < studentGrades.length; i++)
                {
                    if(studentGrades[i] <= fiaRuleAttribute.getMinimumCreditGrade())
                        fiaRuleAttribute.incrementFoundationCredit();
                }

                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    if(fiaRuleAttribute.getFoundationCredit() < fiaRuleAttribute.getAmountOfCreditRequired()
                            && fiaRuleAttribute.getCountCorrectSubjectRequired() != fiaRuleAttribute.getSubjectRequired().size())
                    {
                        return false;
                    }
                }
                else
                {
                    if(fiaRuleAttribute.getFoundationCredit() < fiaRuleAttribute.getAmountOfCreditRequired())
                        return false;
                }
            }
            break;
            case "UEC":
            {
                // First check got required subject or not
                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    for(int i = 0; i < studentSubjects.length; i++)
                    {
                        for(int j = 0; j < fiaRuleAttribute.getSubjectRequired().size(); j++)
                        {
                            if(Objects.equals(studentSubjects[i], fiaRuleAttribute.getSubjectRequired().get(j)))
                            {
                                if(studentGrades[i] <= fiaRuleAttribute.getMinimumGradeRequired().get(j))
                                {
                                    fiaRuleAttribute.incrementCountSubjectRequired();
                                }
                            }
                        }
                    }
                }

                // For every grade, check whether the grade is larger or equal to minimum credit grade
                // Smaller the number the better the grade
                for(int i = 0; i < studentGrades.length; i++)
                {
                    if(studentGrades[i] <= fiaRuleAttribute.getMinimumCreditGrade())
                        fiaRuleAttribute.incrementFoundationCredit();
                }

                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    if(fiaRuleAttribute.getFoundationCredit() < fiaRuleAttribute.getAmountOfCreditRequired()
                            && fiaRuleAttribute.getCountCorrectSubjectRequired() != fiaRuleAttribute.getSubjectRequired().size())
                    {
                        return false;
                    }
                }
                else
                {
                    if(fiaRuleAttribute.getFoundationCredit() < fiaRuleAttribute.getAmountOfCreditRequired())
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
        fiaRuleAttribute.setJoinProgrammeTrue();
        Log.d("FIA joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return fiaRuleAttribute.isJoinProgramme();
    }

    private void setJSONAttribute(String qualificationLevel)
    {
        switch(qualificationLevel)
        {
            case "SPM":
            {
                fiaRuleAttribute.setAmountOfCreditRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getSPM().getAmountOfCreditRequired());
                fiaRuleAttribute.setMinimumCreditGrade(RulePojo.getRulePojo().getAllProgramme().getFia().getSPM().getMinimumCreditGrade());
                fiaRuleAttribute.setGotRequiredSubject(RulePojo.getRulePojo().getAllProgramme().getFia().getSPM().isGotRequiredSubject());
                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    fiaRuleAttribute.setSubjectRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getSPM().getWhatSubjectRequired().getSubject());
                    fiaRuleAttribute.setMinimumGradeRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getSPM().getMinimumSubjectRequiredGrade().getGrade());
                }
            }
            break;
            case "O-Level":
            {
                fiaRuleAttribute.setAmountOfCreditRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getOLevel().getAmountOfCreditRequired());
                fiaRuleAttribute.setMinimumCreditGrade(RulePojo.getRulePojo().getAllProgramme().getFia().getOLevel().getMinimumCreditGrade());
                fiaRuleAttribute.setGotRequiredSubject(RulePojo.getRulePojo().getAllProgramme().getFia().getOLevel().isGotRequiredSubject());
                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    fiaRuleAttribute.setSubjectRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getOLevel().getWhatSubjectRequired().getSubject());
                    fiaRuleAttribute.setMinimumGradeRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getOLevel().getMinimumSubjectRequiredGrade().getGrade());
                }
            }
            break;
            case "UEC":
            {
                fiaRuleAttribute.setAmountOfCreditRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getUEC().getAmountOfCreditRequired());
                fiaRuleAttribute.setMinimumCreditGrade(RulePojo.getRulePojo().getAllProgramme().getFia().getUEC().getMinimumCreditGrade());
                fiaRuleAttribute.setGotRequiredSubject(RulePojo.getRulePojo().getAllProgramme().getFia().getUEC().isGotRequiredSubject());
                if(fiaRuleAttribute.isGotRequiredSubject())
                {
                    fiaRuleAttribute.setSubjectRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getUEC().getWhatSubjectRequired().getSubject());
                    fiaRuleAttribute.setMinimumGradeRequired(RulePojo.getRulePojo().getAllProgramme().getFia().getUEC().getMinimumSubjectRequiredGrade().getGrade());
                }
            }
        }
    }
}
