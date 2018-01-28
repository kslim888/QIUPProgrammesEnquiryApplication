package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "TESL", description = "Entry rule to join TESL")
public class TESL
{
    // advanced math is additional maths
    private static RuleAttribute teslRuleAttribute;

    public TESL() { teslRuleAttribute = new RuleAttribute(); }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's English") String studentEnglishGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            //Check english is pass or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    teslRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    teslRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all students subject check got above at least C or not. At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    teslRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // Check english is credit or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    teslRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    teslRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // Minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul")
                        && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    teslRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // check english
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    teslRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    teslRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student subject, check got minimum grade D. At least D only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    teslRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // Here check english is at least pass (C8) or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "F9"))
                    {
                        teslRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                    break;
                }
            }

            // For all subject check got at least minimum grade B or not. At least B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    teslRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If is enough credit, check english is pass or not
        // If both true, return true as requirements are satisfied
        if(teslRuleAttribute.getUecCredit() >= 5
                || teslRuleAttribute.getALevelCredit() >= 2
                || teslRuleAttribute.getStamCredit() >= 1
                || teslRuleAttribute.getStpmCredit() >= 2)
        {
            if(teslRuleAttribute.isGotEnglishSubjectAndPass())
            {
                return true;
            }
        }
        // Return false as requirements not satisfied
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        teslRuleAttribute.setJoinProgrammeTrue();
        Log.d("TESLjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return teslRuleAttribute.isJoinProgramme();
    }
}
