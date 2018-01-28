package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BioTech", description = "Entry rule to join Bachelor of Science in Biotechnology")
public class BioTech
{
    // Advanced math is additional maths
    private static RuleAttribute bioTechRuleAttribute;

    public BioTech() { bioTechRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // If is STPM qualification
        {
            // For all student subjects, check STPM subject bio physic chemistry is credit or not
            // Only grade C then increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentSubjects[i], "Fizik")
                        && !Objects.equals(studentSubjects[i], "Kimia")
                        && !Objects.equals(studentSubjects[i], "Biology"))
                {
                    if(!Objects.equals(studentGrades[i], "C-")
                            || !Objects.equals(studentGrades[i], "D+")
                            || !Objects.equals(studentGrades[i], "D")
                            || !Objects.equals(studentGrades[i], "F"))
                    {
                        bioTechRuleAttribute.incrementSTPMCredit();
                    }
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // If is A-Level qualification
        {
            // For all student subjects, check A-Level subject bio physic chemistry is credit or not
            // Only grade C then increment
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(!Objects.equals(studentSubjects[i], "Biology")
                        && !Objects.equals(studentSubjects[i], "Physics")
                        && !Objects.equals(studentSubjects[i], "Chemistry"))
                {
                    if(!Objects.equals(studentGrades[i], "D")
                            && !Objects.equals(studentGrades[i], "E")
                            && !Objects.equals(studentGrades[i], "U"))
                    {
                        bioTechRuleAttribute.incrementALevelCredit();
                    }
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // For all subject check got at least minimum grade B or not
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bioTechRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            // TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If enough credit return true
        if(bioTechRuleAttribute.getALevelCredit() >= 2
                || bioTechRuleAttribute.getStpmCredit() >= 2
                || bioTechRuleAttribute.getUecCredit() >= 5)
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
        // If rule is statisfied (return true), this action will be executed
        bioTechRuleAttribute.setJoinProgrammeTrue();
        Log.d("BioTechjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bioTechRuleAttribute.isJoinProgramme();
    }
}
