package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BBA_HospitalityTourismManagement", description = "Entry rule to join BBA in Hospitality & Tourism Management")
public class BBA_HospitalityTourismManagement
{
    private static RuleAttribute bbaOtherRuleAttribute;
    public BBA_HospitalityTourismManagement() {
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
                    bbaOtherRuleAttribute.incrementCountSTPM(1);
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
                    bbaOtherRuleAttribute.incrementCountSTAM(1);
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
                    bbaOtherRuleAttribute.incrementCountALevel(1);
                }
            }
        }
        else if (Objects.equals(qualificationLevel, "UEC")) // if is UEC qualifcation
        {
            for (int i = 0; i < studentGrades.length; i++) // for all students
            {
                if (!Objects.equals(studentGrades[i], "C7") && !Objects.equals(studentGrades[i], "C8") && !Objects.equals(studentGrades[i], "F9"))
                {
                    bbaOtherRuleAttribute.incrementCountUEC(1);
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO minimum CGPA of 2.00 out of 4.00
        }

        if (bbaOtherRuleAttribute.getCountALevel() >= 2 ||
                bbaOtherRuleAttribute.getCountSTAM() >= 1 ||
                bbaOtherRuleAttribute.getCountSTPM() >= 2 ||
                bbaOtherRuleAttribute.getCountUEC() >= 5)
        {
            return true;
        }
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bbaOtherRuleAttribute.setJoinProgramme(true);
        Log.d("hospitalityJoin ", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbaOtherRuleAttribute.isJoinProgramme();
    }
}
