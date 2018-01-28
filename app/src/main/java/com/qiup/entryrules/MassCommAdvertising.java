package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "MassCommAdvertising", description = "Entry rule to join Bachelor of Mass Communication Advertising")
public class MassCommAdvertising
{
    // Advanced math is additional maths
    private static RuleAttribute advertisingRuleAttribute;

    public MassCommAdvertising() { advertisingRuleAttribute = new RuleAttribute(); }

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
            //check english at SPM or O-Level is credit or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "G"))
                {
                    advertisingRuleAttribute.setGotEnglishSubjectAndCredit();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "F")
                        && !Objects.equals(studentEnglishGrade, "G")
                        && !Objects.equals(studentEnglishGrade, "U"))
                {
                    advertisingRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    advertisingRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check english at SPM / O-Level is credit or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "G"))
                {
                    advertisingRuleAttribute.setGotEnglishSubjectAndCredit();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "F")
                        && !Objects.equals(studentEnglishGrade, "G")
                        && !Objects.equals(studentEnglishGrade, "U"))
                {
                    advertisingRuleAttribute.setGotEnglishSubjectAndCredit();
                }
            }

            // minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    advertisingRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // Check english at SPM or O-Level is pass or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    advertisingRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    advertisingRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student subject, check got minimum grade D. At least D only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    advertisingRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // Here check english is at credit or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                // Check english is credit or not
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        advertisingRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    advertisingRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check enough credit or not. If enough, check english is credit or not
        // If both true, return true as requirements satisfied
        if(advertisingRuleAttribute.getUecCredit() >= 5
                || advertisingRuleAttribute.getStamCredit() >= 1
                || advertisingRuleAttribute.getStpmCredit() >= 2)
        {
            if(advertisingRuleAttribute.isGotEnglishSubjectAndCredit())
            {
                return true;
            }
        }
        // For A-Level, requirements is check english is pass and credit is at least 2
        if(advertisingRuleAttribute.getALevelCredit() >= 2)
        {
            if(advertisingRuleAttribute.isGotEnglishSubjectAndPass())
            {
                return true;
            }
        }
        // Return false as requirements is not satisfied
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // If rule is statisfied (return true), this action will be executed
        advertisingRuleAttribute.setJoinProgrammeTrue();
        Log.d("MassCommAdvertising", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return advertisingRuleAttribute.isJoinProgramme();
    }

}
