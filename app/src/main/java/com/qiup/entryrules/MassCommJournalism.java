package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "MassCommJournalism", description = "Entry rule to join Bachelor of Mass Communication Journalism")
public class MassCommJournalism
{
    // Advanced math is additional maths
    private static RuleAttribute journalismRuleAttribute;

    public MassCommJournalism() { journalismRuleAttribute = new RuleAttribute(); }

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
            // Check english at SPM / O-Level
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "G"))
                {
                    journalismRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    journalismRuleAttribute.setGotEnglishSubjectAndCredit();
                }
            }

            // For all students subject check at least C or not. At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    journalismRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // Check english got credit or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "G"))
                {
                    journalismRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    journalismRuleAttribute.setGotEnglishSubjectAndCredit();
                }
            }

            // Minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul")
                        && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    journalismRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For A-Level, check english got at least pass or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    journalismRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    journalismRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student subject, check got minimum grade D. At least D only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    journalismRuleAttribute.incrementALevelCredit();
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
                        journalismRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    journalismRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // For A-Level, requirements is check english is pass and credit is at least 2
        if(journalismRuleAttribute.getALevelCredit() >= 2)
        {
            if(journalismRuleAttribute.isGotEnglishSubjectAndPass())
            {
                return true;
            }
        }
        // Check enough credit or not. If enough, check english is credit or not
        // If both true, return true as requirements satisfied
        if(journalismRuleAttribute.getUecCredit() >= 5
                || journalismRuleAttribute.getStamCredit() >= 1
                || journalismRuleAttribute.getStpmCredit() >= 2)
        {
            if(journalismRuleAttribute.isGotEnglishSubjectAndCredit())
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
        journalismRuleAttribute.setJoinProgrammeTrue();
        Log.d("MassCommJournalism", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return journalismRuleAttribute.isJoinProgramme();
    }

}
