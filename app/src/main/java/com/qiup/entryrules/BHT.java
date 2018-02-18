package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BHT", description = "Entry rule to join BBA in Hospitality & Tourism Management")
public class BHT
{
    private static RuleAttribute bbaOtherRuleAttribute;
    public BHT() {
        bbaOtherRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if (Objects.equals(qualificationLevel, "STPM")) // if is STPM qualifcation
        {
            // minimum grade C only increment
            for (int i = 0; i < studentGrades.length; i++) // for all student subject
            {
                if (!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bbaOtherRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "STAM"))  // if is STAM qualifcation
        {
            // minimum grade of Jayyid only increment
            for (int i = 0; i < studentGrades.length; i++)  // for all student subject
            {
                if (!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bbaOtherRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualifcation
        {
            // minimum grade of E(pass) only increment
            for (int i = 0; i < studentGrades.length; i++)  // for all student subject
            {
                if (!Objects.equals(studentGrades[i], "U"))
                {
                    bbaOtherRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "UEC")) // if is UEC qualifcation
        {
            for (int i = 0; i < studentGrades.length; i++) // for all students
            {
                if (!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bbaOtherRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA of 2.00 out of 4.00
        }

        // if enough credit, return true
        if (bbaOtherRuleAttribute.getALevelCredit() >= 2 ||
                bbaOtherRuleAttribute.getStamCredit() >= 1 ||
                bbaOtherRuleAttribute.getStpmCredit() >= 2 ||
                bbaOtherRuleAttribute.getUecCredit() >= 5)
        {
            return true;
        }
        // if not enough credit, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bbaOtherRuleAttribute.setJoinProgrammeTrue();
        Log.d("hospitalityJoin ", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbaOtherRuleAttribute.isJoinProgramme();
    }
}
