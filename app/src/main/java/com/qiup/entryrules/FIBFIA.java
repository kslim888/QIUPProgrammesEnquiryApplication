package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "FIB/FIA", description = "Entry rule to join Foundation in Business or Foundation in Arts")
public class FIBFIA
{
    private static RuleAttribute fibfiaRuleAttribute;
    public FIBFIA() {
        fibfiaRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        // All qualification check grade is Credit or not.
        // Only credit then increment
        if(Objects.equals(qualificationLevel, "SPM"))
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                    fibfiaRuleAttribute.incrementFoundationCredit();
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level"))
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                    fibfiaRuleAttribute.incrementFoundationCredit();
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC"))// is UEC
        {
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                    fibfiaRuleAttribute.incrementFoundationCredit();
            }
        }

        // If credit not enough, return false
        if(!Objects.equals(qualificationLevel, "UEC")) // If is SPM or O-Level
        {
            if(fibfiaRuleAttribute.getFoundationCredit() < 5)
                return false;
        }
        else // is UEC
        {
            if(fibfiaRuleAttribute.getFoundationCredit() < 3)
                return false;
        }
        // If requirements is satiafied, return true
        return true;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        fibfiaRuleAttribute.setJoinProgrammeTrue();
        Log.d("FIBFIA joinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return fibfiaRuleAttribute.isJoinProgramme();
    }
}
