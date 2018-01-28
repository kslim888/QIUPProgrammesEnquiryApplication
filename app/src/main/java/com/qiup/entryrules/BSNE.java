package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BSNE", description = "Entry rule to join Bachelor of Special Needs Education")
public class BSNE
{
    // Advanced math is additional maths
    private static RuleAttribute bsneRuleAttribute;

    public BSNE() {
        bsneRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // If is STPM qualification
        {
            // For all students subject check  at least C or not. At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bsneRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // If is STAM qualification
        {
            // Minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bsneRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // If is A-Level qualification
        {
            // For all student subject, check got minimum grade C.
            // At least C (full passes) only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    bsneRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all subject check got at least minimum grade B or not. At least B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bsneRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If enough credit return true
        if(bsneRuleAttribute.getUecCredit() >= 5
                || bsneRuleAttribute.getALevelCredit() >= 2
                || bsneRuleAttribute.getStamCredit() >= 1
                || bsneRuleAttribute.getStpmCredit() >= 2)
        {
            return true;
        }
        // If not enough credit, return false as requirements not satisfied
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bsneRuleAttribute.setJoinProgrammeTrue();
        Log.d("SpecialNeedsEdu", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bsneRuleAttribute.isJoinProgramme();
    }
}
