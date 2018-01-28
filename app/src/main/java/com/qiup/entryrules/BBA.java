package com.qiup.entryrules;

import android.util.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

import java.util.Objects;

@Rule(name = "BBA", description = "Entry rule to join Bachelor of Business Administration")
public class BBA
{
    // Advanced math is additional maths
    private static RuleAttribute bbaRuleAttribute;

    public BBA() {
        bbaRuleAttribute = new RuleAttribute();
    }

    // when
    @Condition
    public boolean allowToJoin(@Fact("Qualification Level") String qualificationLevel,
                               @Fact("Student's Subjects")String[] studentSubjects,
                               @Fact("Student's Grades")String[] studentGrades,
                               @Fact("Student's SPM or O-Level") String studentSPMOLevel,
                               @Fact("Student's Mathematics") String studentMathematicsGrade,
                               @Fact("Student's English") String studentEnglishGrade,
                               @Fact("Student's Additional Mathematics") String studentAddMathGrade)
    {
        if(Objects.equals(qualificationLevel, "STPM")) // if is STPM qualification
        {
            // for all students subject check got mathematics and english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Matematik (M)")
                        || Objects.equals(studentSubjects[i], "Matematik (T)"))
                {
                    bbaRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                {
                    bbaRuleAttribute.setGotEnglishSubject();
                }
                if(bbaRuleAttribute.isGotMathSubject() &&  bbaRuleAttribute.isGotEnglishSubject())
                {
                    break;
                }
            }

            //If STPM got math subject
            if(bbaRuleAttribute.isGotMathSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Matematik (M)")
                            || Objects.equals(studentSubjects[i], "Matematik (T)"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            bbaRuleAttribute.setGotMathSubjectAndPass();
                            break;
                        }
                    }
                }
            }

            if(bbaRuleAttribute.isGotEnglishSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Kesusasteraan Inggeris"))
                    {
                        if(!Objects.equals(studentGrades[i], "F"))
                        {
                            bbaRuleAttribute.setGotEnglishSubjectAndPass();
                        }
                        break;
                    }
                }
            }

            // if stpm got math subject but not pass, or no math subject
            if(!bbaRuleAttribute.isGotMathSubjectAndPass())
            {
                // check maths
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                }
                else // is o-level
                {
                    // check maths and add maths pass or not. if no pass return false
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                }
            }

            // if stpm got english subject but not pass, or no english subject at STPM
            if(!bbaRuleAttribute.isGotEnglishSubjectAndPass())
            {
                // check english
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    // if fail, return false
                    if(!Objects.equals(studentEnglishGrade, "G"))
                    {
                        bbaRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
                else // is o-level
                {
                    // if fail, return false
                    if(!Objects.equals(studentEnglishGrade, "U"))
                    {
                        bbaRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // for all students subject check got above at least C or not. At least C only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C-")
                        && !Objects.equals(studentGrades[i], "D+")
                        && !Objects.equals(studentGrades[i], "D")
                        && !Objects.equals(studentGrades[i], "F"))
                {
                    bbaRuleAttribute.incrementSTPMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "STAM")) // if is STAM qualification
        {
            // check maths and english
            if(Objects.equals(studentSPMOLevel, "SPM"))
            {
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "G"))
                {
                    bbaRuleAttribute.setGotMathSubjectAndPass();
                }
                else if(!Objects.equals(studentMathematicsGrade, "G"))
                {
                    bbaRuleAttribute.setGotMathSubjectAndPass();
                }

                if(!Objects.equals(studentEnglishGrade, "G"))
                {
                    bbaRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }
            else // is o-level
            {
                // check maths and add maths pass or not. if no pass return false
                if(!Objects.equals(studentAddMathGrade, "None")
                        && !Objects.equals(studentAddMathGrade, "U"))
                {
                    bbaRuleAttribute.setGotMathSubjectAndPass();
                }
                else if(!Objects.equals(studentMathematicsGrade, "U"))
                {
                    bbaRuleAttribute.setGotMathSubjectAndPass();
                }

                // check english
                if(!Objects.equals(studentEnglishGrade, "U"))
                {
                    bbaRuleAttribute.setGotEnglishSubjectAndPass();
                }
            }

            // minimum grade of Jayyid, only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "Maqbul") && !Objects.equals(studentGrades[i], "Rasib"))
                {
                    bbaRuleAttribute.incrementSTAMCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "A-Level")) // if is A-Level qualification
        {
            // for all students subject check got mathematics and english subject or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Further Mathematics"))
                {
                    bbaRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "Literature in English"))
                {
                    bbaRuleAttribute.setGotEnglishSubject();
                }
                if(bbaRuleAttribute.isGotMathSubject() && bbaRuleAttribute.isGotEnglishSubject())
                {
                    break;
                }
            }

            // If A-Level got math subject, check is pass or not
            if(bbaRuleAttribute.isGotMathSubject() )
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Mathematics")
                            || Objects.equals(studentSubjects[i], "Further Mathematics"))
                    {
                        if(!Objects.equals(studentGrades[i], "U"))
                        {
                            bbaRuleAttribute.setGotMathSubjectAndPass();
                            break;
                        }
                    }
                }
            }

            // If A-Level got english subject, check is pass or not
            if(bbaRuleAttribute.isGotEnglishSubject())
            {
                for(int i = 0; i < studentSubjects.length; i++)
                {
                    if(Objects.equals(studentSubjects[i], "Literature in English"))
                    {
                        if(!Objects.equals(studentGrades[i], "U"))
                        {
                            bbaRuleAttribute.setGotEnglishSubjectAndPass();
                        }
                        break;
                    }
                }
            }

            // if A-level got math subject but not pass, or no math subject at a-level
            if(!bbaRuleAttribute.isGotMathSubjectAndPass())
            {
                // check maths
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "G"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "G"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                }
                else // is o-level
                {
                    // check maths and add maths pass or not. if no pass return false
                    if(!Objects.equals(studentAddMathGrade, "None")
                            && !Objects.equals(studentAddMathGrade, "U"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                    else if(!Objects.equals(studentMathematicsGrade, "U"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                }
            }

            // if A-level got english subject but not pass, or no eng subject
            if(!bbaRuleAttribute.isGotEnglishSubjectAndPass())
            {
                // check english
                if(Objects.equals(studentSPMOLevel, "SPM")) // if is SPM
                {
                    if(!Objects.equals(studentEnglishGrade, "G"))
                    {
                        bbaRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
                else // is o-level
                {
                    // check english
                    if(!Objects.equals(studentEnglishGrade, "U"))
                    {
                        bbaRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // for all student subject, check got minimum grade E (pass).
            // At least E only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "U"))
                {
                    bbaRuleAttribute.incrementALevelCredit();
                }
            }
        }
        else if(Objects.equals(qualificationLevel, "UEC")) // if is UEC qualification
        {
            // Check got advanced maths and is fail or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Mathematics")
                        || Objects.equals(studentSubjects[i], "Additional Mathematics"))
                {
                    bbaRuleAttribute.setGotMathSubject();
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    bbaRuleAttribute.setGotEnglishSubject();
                }
            }

            if(!bbaRuleAttribute.isGotMathSubject() || !bbaRuleAttribute.isGotEnglishSubject())
            {
                return false;
            }

            // Check advanced math, math and english got at least pass or not
            for(int i = 0; i < studentSubjects.length; i++)
            {
                if(Objects.equals(studentSubjects[i], "Additional Mathematics")
                        || Objects.equals(studentSubjects[i], "Mathematics"))
                {
                    if(!Objects.equals(studentGrades[i], "F9"))
                    {
                        bbaRuleAttribute.setGotMathSubjectAndPass();
                    }
                }
                if(Objects.equals(studentSubjects[i], "English"))
                {
                    if(!Objects.equals(studentGrades[i], "F9"))
                    {
                        bbaRuleAttribute.setGotEnglishSubjectAndPass();
                    }
                }
            }

            // For all subject check got at least minimum grade B or not.
            // At least B only increment
            for(int i = 0; i < studentGrades.length; i++)
            {
                if(!Objects.equals(studentGrades[i], "C7")
                        && !Objects.equals(studentGrades[i], "C8")
                        && !Objects.equals(studentGrades[i], "F9"))
                {
                    bbaRuleAttribute.incrementUECCredit();
                }
            }
        }
        else // Foundation / Program Asasi / Asas / Matriculation / Diploma
        {
            //TODO Foundation / Program Asasi / Asas / Matriculation / Diploma
        }

        // If all credit is enough, check math and english is pass or not
        if(bbaRuleAttribute.getALevelCredit() >= 2
                || bbaRuleAttribute.getStamCredit() >= 1
                || bbaRuleAttribute.getStpmCredit() >= 2
                || bbaRuleAttribute.getUecCredit() >= 5)
        {
            // If math and english is pass, return true as all requirements satisfied
            if(bbaRuleAttribute.isGotMathSubjectAndPass()
                    && bbaRuleAttribute.isGotEnglishSubjectAndPass())
            {
                return true;
            }
        }
        // if requirements not satisfy, return false
        return false;
    }

    //then
    @Action
    public void joinProgramme() throws Exception
    {
        // if rule is statisfied (return true), this action will be executed
        bbaRuleAttribute.setJoinProgrammeTrue();
        Log.d("BBAjoinProgramme", "Joined");
    }

    public static boolean isJoinProgramme()
    {
        return bbaRuleAttribute.isJoinProgramme();
    }
}
