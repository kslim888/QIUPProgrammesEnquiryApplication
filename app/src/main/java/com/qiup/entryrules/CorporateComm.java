package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "CorporateComm", description = "Entry rule to join Bachelor of Corporate Communication")
public class CorporateComm
{
    // advanced math is additional maths
    private static RuleAttribute corporateCommRuleAttribute;

    public CorporateComm() { corporateCommRuleAttribute = new RuleAttribute(); }

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
            // Check english it is credit or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "G"))
                {
                    corporateCommRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    corporateCommRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    corporateCommRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // Check english it is credit or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "D")
                        && !Objects.equals(studentEnglishGrade, "E")
                        && !Objects.equals(studentEnglishGrade, "G"))
                {
                    corporateCommRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    corporateCommRuleAttribute.setGotEnglishSubjectAndCredit();
                }
            }

            // Minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul")
                        && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    corporateCommRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // For A-level, check english is at least pass or not
            if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
            {
                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    corporateCommRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    corporateCommRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // For all student subject, check got minimum grade D. At least D only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "E")
                        && !Objects.equals(studentGrades[i], "U"))
                {
                    corporateCommRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            for(int i = 0; i < studentSubjects.length; i++)
            {
                // Check english is credit or not
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "C7")
                            && !Objects.equals(studentGrades[i], "C8")
                            && !Objects.equals(studentGrades[i], "F9"))
                    {
                        corporateCommRuleAttribute.setGotEnglishSubjectAndCredit();
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
                    corporateCommRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // Check it is enough credit or not. If is enough credit, check english is credit or not
        // If both true, return true as all requirements satisfied
        if(corporateCommRuleAttribute.getUecCredit() >= 5
                || corporateCommRuleAttribute.getStamCredit() >= 1
                || corporateCommRuleAttribute.getStpmCredit() >= 2)
        {
            if(corporateCommRuleAttribute.isGotEnglishSubjectAndCredit())
            {
                return true;
            }
        }
        // A-level need check english is pass or not and credit is at least 2
        if(corporateCommRuleAttribute.getALevelCredit() >= 2)
        {
            if(corporateCommRuleAttribute.isGotEnglishSubjectAndPass())
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
        corporateCommRuleAttribute.setJoinProgrammeTrue();
        Log.d("CorporateComm", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return corporateCommRuleAttribute.isJoinProgramme();
    }

}
