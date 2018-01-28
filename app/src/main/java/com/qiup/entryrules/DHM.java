package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "DHM", description = "Entry rule to join Diploma in Hotel Management")
public class DHM
{
    private static RuleAttribute dhmRuleAttribute;

    public DHM() {
        dhmRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "SPM")) // if is SPM qualification
        {
            // For all student's grade, check at least grade C or not
            // Only at least grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "G"))
                {
                    dhmRuleAttribute.incrementSPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "O-Level")) // if is O-Level qualification
        {
            // For all student's grade, check at least grade C or not
            // Only at least grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "F")
                        && !Objects.equals(studentGrades[i], "G")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dhmRuleAttribute.incrementOLevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // For all student's grade, check at least grade C or not
            // Only at least grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    dhmRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For all student's grade, check at least grade C or not
            // Only at least grade C, then increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    dhmRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // minimum grade of Maqbul, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if( !Objects.equals(studentGrades[i], "Rasib"))
                {
                    dhmRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // for all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    dhmRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // SKM level 3
        {
            // TODO SKM level 3
        }

        // if credit is enough, requirements satisfy = return true
        if(dhmRuleAttribute.getSpmCredit() >= 3
                || dhmRuleAttribute.getoLevelCredit() >= 3
                || dhmRuleAttribute.getUecCredit() >= 3
                || dhmRuleAttribute.getStamCredit() >= 1
                || dhmRuleAttribute.getStpmCredit() >= 1
                || dhmRuleAttribute.getALevelCredit() >= 1)
        {
                return true;
        }
        // if credit not enough, requirements not satisfy = return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        dhmRuleAttribute.setJoinProgrammeTrue();
        Log.d("DiplomaHotelManagement", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return dhmRuleAttribute.isJoinProgramme();
    }
}